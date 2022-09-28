package ku.cs.application.services;

public interface OfficerDataSource<T> {

    T readData1();
    T readData2();
    T readData3();
    T readData4();

    void writeData(T t);
}
