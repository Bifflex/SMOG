package uqac.dim.smogmain;

public class Film {

    private String title;
    private String real;
    private String date;
    private String note;

    public Film() {
    }

    public Film(String title, String real, String date, String note) {
        this.title = title;
        this.real = real;
        this.date = date;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReal() {
        return real;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
