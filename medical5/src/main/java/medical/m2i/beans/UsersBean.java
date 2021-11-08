package medical.m2i.beans;

import java.io.Serializable;
import java.util.List;

import medical.m2i.dao.UserDao;
import medical.m2i.model.User;

//@ManagedBean
//@SessionScoped
public class UsersBean implements Serializable {
	// UsersBean

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356685251785460886L;

	private List<User> listeUsers;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListeUsers() {
		return listeUsers;
	}

	public void setListeUsers(List<User> listeUsers) {
		this.listeUsers = listeUsers;
	}

	public UsersBean() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		UserDao usdao = new UserDao();
		listeUsers = usdao.getUsers();

		user = new User();
		System.out.println("Je suis dans init bean User , taille de la liste = " + listeUsers.size());
	}

	public String saveUser() throws ClassNotFoundException {
		UserDao usDao = new UserDao();
		user.setRoles("ROLE_USER");
		user.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
		user.setName("Michel");
		user.setPhotouser("user1.png");
		usDao.registerUser(user);
		System.out.println(user.getUsername());
		return "/done.xhtml?faces-redirect=true";
	}

}
