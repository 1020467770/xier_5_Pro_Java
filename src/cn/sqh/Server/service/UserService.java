package cn.sqh.Server.service;

import cn.sqh.Server.domain.BasicFile;
import cn.sqh.Server.domain.User;

import java.util.ArrayList;

public interface UserService {

    User signUp(String spUserName, String spUserPassword);

    User login(User user);

    void uploadPicture(String username, BasicFile file);

    void createNewFolder(String creatorUsername, BasicFile newFolder);

    ArrayList<BasicFile> getAllFilesByUserIdAndParentFolderId(int userId, int parentFolderId);

    boolean isHavaEnoughCapicity(String username);

    Boolean deleteFile(int fileId);

    Boolean deleteLocalFile(int fileId);

    boolean activeUser(String code);

    Boolean tryChangePsw(int userId, String newPassword, int tryType);

    boolean changePassword(String code);
}
