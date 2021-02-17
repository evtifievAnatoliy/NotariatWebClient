package ru.notariat.client.db.interfaces;

import ru.notariat.client.db.objects.User;
import ru.notariat.client.exeptions.MySQLExeptions;

public interface UsersDB{

	public int getUserId (User user);
	public boolean checkUserByEmailAndPass (User user) throws MySQLExeptions;
	public boolean chekUserGroup(User user, String groupType) throws MySQLExeptions;
}
