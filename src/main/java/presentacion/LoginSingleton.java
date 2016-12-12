package presentacion;


public class LoginSingleton {
	
	private String nickname;
	private boolean sessionActiva;	
	private static LoginSingleton loginSingleton;
	
	
	private LoginSingleton(String nickname){
		this.nickname = nickname;
		this.sessionActiva = true;
	}
	
	public static LoginSingleton getInstance(String nickname){
		if(loginSingleton == null){
			loginSingleton = new LoginSingleton(nickname);
			System.out.println("------------------->>>>1");
		}
		return loginSingleton;
	}
	
	public void cerrarSession(){
		this.sessionActiva = false;
		loginSingleton = null;
		System.out.println("_________________________>>>>>>>>>>2");
		
	}
	
	public boolean isSessionActiva() {
		return sessionActiva;
	}
	
}
