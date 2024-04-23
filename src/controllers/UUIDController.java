package controllers;
import java.util.UUID;

public class UUIDController {
	
	public String generateUUID() {
		UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
	}
}
