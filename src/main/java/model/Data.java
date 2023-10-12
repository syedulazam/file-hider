package model;

public class Data {
    private int id;
    private String fileName;
    private String email;
    private String path;

    public Data(int id, String path,String fileName){
        this.id = id;
        this.fileName = fileName;
        this.path = path;
    }

    public Data(int id, String fileName, String email, String path){
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

}
