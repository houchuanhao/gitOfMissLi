package com.model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.model.user.User;

public interface FileService {
	public int getByFName(String fileName);
	public String save(MyFile myFile) throws FileNotFoundException, IOException;
	public List<MyFile> getFileByUId(String uId);
	public List<MyFile> getFileByBId(String bId);
	public void delete(int fileId);
	public void delete(MyFile myFile );
	public MyFile getById(int id);
}
