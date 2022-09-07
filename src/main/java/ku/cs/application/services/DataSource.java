package ku.cs.application.services;

public interface DataSource<T> {

    T readData();
    void  writeData(T t);

}
