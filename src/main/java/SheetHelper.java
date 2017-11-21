import annotations.ExcelField;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.List;

// TODO thought about pattern builder
public class SheetHelper<T> {

    private Sheet sheet;

    public SheetHelper(Workbook workbook, String sheetName) {
        this.sheet = workbook.createSheet(sheetName);
    }

    public void addSheet(List<T> tList) {

        for (int i = 0; i < tList.size(); i++) {
            if (i == 0) {
                writeHeader(tList.get(i));
            }

            Row row = sheet.createRow(i + 1);
            writeRow(tList.get(i), row);
        }
    }

    private void writeHeader(Object instance) {
        Class type = instance.getClass();

        Row header = sheet.createRow(0);

        // TODO Use Java 8. Stream.
        int cellCounter = 0;

        for (Field field: type.getDeclaredFields()) {
            ExcelField annotation = field.getAnnotation(ExcelField.class);

            if (annotation != null) {
                header.createCell(cellCounter++).setCellValue(annotation.value());
            }
        }
    }

    private void writeRow(Object instance, Row row) {
        Class type = instance.getClass();

        // TODO Use Java 8. Stream.
        int cellCounter = 0;
        for (Field field: type.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getAnnotation(ExcelField.class) != null) {
                try {
                    row.createCell(cellCounter++).setCellValue(field.get(instance).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
