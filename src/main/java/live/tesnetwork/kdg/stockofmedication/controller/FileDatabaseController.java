package live.tesnetwork.kdg.stockofmedication.controller;

import live.tesnetwork.kdg.stockofmedication.entity.Medication;
import live.tesnetwork.kdg.stockofmedication.entity.User;
import live.tesnetwork.kdg.stockofmedication.entity.UserMedication;
import live.tesnetwork.kdg.stockofmedication.enums.TimeUnits;
import live.tesnetwork.kdg.stockofmedication.utils.Config;
import live.tesnetwork.kdg.stockofmedication.utils.Convertable;
import live.tesnetwork.kdg.stockofmedication.utils.EncryptionHelper;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FileDatabaseController implements DatabaseController {
    private final Config config;
    private final String rootPath;

    private final String medicationPath;

    private final String userMedicationPath;
    private final String userPath;

    public FileDatabaseController(Config config) {
        this.config = config;
        this.rootPath = config.get("PATH_ROOT");
        this.medicationPath = rootPath + config.get("MEDICATION_SUB_PATH");
        this.userMedicationPath = rootPath + config.get("USER_MEDICATION_SUB_PATH");
        this.userPath = rootPath + config.get("USERS_SUB_PATH");
    }

    @Override
    public boolean saveMedication(Medication medication) {
        File file = getOrCreateFile(medicationPath, medication.getFullName());
        return writeToFile(file, medication);
    }

    @Override
    public boolean deleteMedication(String name) {
        File file = getOrCreateFile(medicationPath, name, false);
        if (file.exists()) return file.delete();
        return true;
    }

    @Nullable
    @Override
    public Medication getMedication(String name) {
        File file = getOrCreateFile(medicationPath, name, false);
        if (!file.exists()) return null;
        return readFromFile(file);
    }

    @Override
    public List<Medication> getMedications() {
        File path = new File(medicationPath);
        if (!path.exists()) path.mkdirs();
        return Arrays.stream(path.listFiles())
                .map(this::readFromFile)
                .filter(Medication.class::isInstance)
                .map(Medication.class::cast)
                .toList();
    }

    @Override
    public UserMedication getUserMedication(String id, String name) {
        File file = getOrCreateFile(userMedicationPath, id, name, false);
        if (!file.exists()) return null;
        return readFromFile(file);
    }

    @Override
    public List<UserMedication> getUserMedications(String id) {
        File path = new File(userMedicationPath + id);
        if (!path.exists()) path.mkdirs();
        File[] files = path.listFiles();
        return Arrays.stream(files)
                .map(this::readFromFile)
                .filter(UserMedication.class::isInstance)
                .map(UserMedication.class::cast)
                .toList();
    }

    @Override
    public boolean saveUserMedication(String id, UserMedication userMedication) {
        File file = getOrCreateFile(userMedicationPath, id, userMedication.getMedication().getFullName());
        System.out.println(userMedicationPath);
        System.out.println(id);
        System.out.println(userMedication.getMedication().getFullName());
        System.out.println(file.getAbsolutePath());
        System.out.println(userMedicationPath + id + userMedication.getMedication().getFullName());
        return writeToFile(file, userMedication);
    }

    public boolean deleteUserMedication(String id, UserMedication userMedication) {
        File file = getOrCreateFile(userMedicationPath, id, userMedication.getMedication().getFullName(), false);
        if (file.exists()) return file.delete();
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public User getUserByUsername(String username) {
        File file = getOrCreateFile(userPath, username, false);
        if (file.exists()) return readFromFile(file, User.class);
        else return null;
    }

    @Override
    public boolean createUser(String username, String password) {
        try {
            File file = getOrCreateFile(userPath, username, false);
            if (file.exists()) return false;
            file.getParentFile().mkdirs();
            file.createNewFile();
            return writeToFile(file, new User(username, EncryptionHelper.hashPassword(password)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getOrCreateFile(String path, String fileName) {
        return getOrCreateFile(path + fileName);
    }

    public File getOrCreateFile(String path, String path2, String fileName) {
        return getOrCreateFile(path + path2 + "/" + fileName);
    }

    public File getOrCreateFile(String path, String path2, String fileName, boolean createIfNotExist) {
        return getOrCreateFile(path + path2 + "/" + fileName, createIfNotExist);
    }

    public File getOrCreateFile(String path, String fileName, boolean createIfNotExist) {
        return getOrCreateFile(path + fileName, createIfNotExist);
    }

    public File getOrCreateFile(String path) {
        return getOrCreateFile(path, true);
    }

    public File getOrCreateFile(String path, boolean createIfNotExist) {
        File file = new File(path);
        if (!file.exists() && createIfNotExist) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Could not create file");
            }
        }
        return file;
    }


    private boolean writeToFile(File file, Convertable convertable) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("@%s\n".formatted(convertable.getClass().getSimpleName()));
            convertable.toMap().forEach((key, value) -> {
                try {
                    writer.write("%s;%s\n".formatted(key, escapeSpecialCharacters(value)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private <T extends Convertable> T readFromFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            String fileType = scanner.next();
            scanner.close();
            switch (fileType) {
                case "@Medication":
                    return (T) readFromFile(file, Medication.class);
                case "@User":
                    return (T) readFromFile(file, User.class);
                case "@UserMedication":
                    return (T) readFromFile(file, UserMedication.class);
                default:
                    throw new IllegalArgumentException("Invalid file type");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends Convertable> T readFromFile(File file, Class<T> object) {
        Map<String, String> map = new HashMap<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            String fileType = scanner.next();
            if (fileType.isBlank() || fileType.equals("@%s\n".formatted(object.getSimpleName()))) {
                throw new IllegalArgumentException("Invalid file type");
            }
            while (scanner.hasNext()) {
                String fileContents = scanner.next();
                if (!fileContents.startsWith("@")) {
                    String[] v = fileContents.split(";");
                    map.put(v[0], v[1]);
                }
            }
            scanner.close();
            return Convertable.to(map, object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String escapeSpecialCharacters(String str) {
        return str.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
    }

    @Override
    public void updateStockFromTakeIn(String id, boolean canGoNegative) {
        LocalDateTime now = LocalDateTime.now();
        List<UserMedication> userMedications = getUserMedications(id)
                .stream()
                .filter(userMedication -> userMedication.getAmountPerTimeUnit() > 0)
                .filter(userMedication -> userMedication.getAmountOfTimeUnit() > 0)
                .filter(userMedication -> userMedication.getTimeUnit() != null)
                .toList();
        for (UserMedication userMedication : userMedications) {
            TimeUnits timeUnit = userMedication.getTimeUnit();
            LocalDateTime lastTaken = userMedication.getLastTaken()
                    .plus(userMedication.getAmountOfTimeUnit(), timeUnit.getUnit());
            while (lastTaken.isBefore(now)) {
                Integer amount = userMedication.getAmountPerTimeUnit();
                if (canGoNegative || userMedication.getStock() - amount >= 0) {
                    userMedication.setStock(userMedication.getStock() - amount);
                    userMedication.setLastTaken(lastTaken);
                    lastTaken = lastTaken.plus(userMedication.getAmountOfTimeUnit(), timeUnit.getUnit());
                } else break;
            }
            saveUserMedication(id, userMedication);
        }
    }

    @Override
    public boolean deleteUser(String newUser) {
        File file = getOrCreateFile(userPath, newUser, false);
        if (file.exists()) return file.delete();
        return true;
    }
}
