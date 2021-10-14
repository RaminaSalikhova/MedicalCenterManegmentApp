package models.AnswerTransferModels;

import enums.TYPE;

import java.io.Serializable;

public class UserAtm implements Serializable {
    private int Id;
    private int type;
    private String FIO;

    public String getFIO() {
        return FIO;
    }

    public int getId() {
        return Id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        if (type == TYPE.CLIENT.ordinal()) {
            return "Тип учетной записи - пациент,  ФИО пациента: " + FIO;
        } else if (type == TYPE.DOCTOR.ordinal()) {
            return "Тип учетной записи - доктор,  ФИО доктора: " + FIO;
        }

        return "Тип учетной записи - администратор,  ФИО администратора: " + FIO;

    }
}