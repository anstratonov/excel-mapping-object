import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExcelMapperTest {

    Path temporaryExecl;
    Workbook workbook;

    @Before
    public void init() {
        workbook = new HSSFWorkbook();

        try {
            temporaryExecl = Files.createTempFile("sample_xlsx", ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sampleTest() {
        List<User> userList = DataSource.getUsers();

        new SheetHelper<User>(workbook, "test").addSheet(userList);

        try {
            workbook.write(new FileOutputStream(temporaryExecl.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
