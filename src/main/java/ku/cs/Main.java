package ku.cs;

import ku.cs.application.models.Users;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        File destDir = new File("");
        String filename = "default.png";
        Path delete = FileSystems.getDefault().getPath(
                destDir.getAbsolutePath()+"\\data\\images\\profile\\2022-10-03_1664812275102.PNG");
        System.out.println(delete);
    }
}
