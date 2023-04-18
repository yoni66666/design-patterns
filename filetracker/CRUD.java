package il.co.ilrd.filetracker;

public interface CRUD<K,D>{
    public K create(D data);
    public D read(K line);
    public void update(K line, D data);
    public void delete(K line);
}