package ku.cs.application.services;

import ku.cs.application.models.ComplaintList;

public interface DataSource<T> {

    T readData();
    void  writeData(T t);


}
