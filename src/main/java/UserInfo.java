import java.time.LocalDateTime;

public class UserInfo {

	private String id;
	private boolean is_corporate;
	private LocalDateTime generate_time;

	public UserInfo() {
	}

	public UserInfo(String id, boolean is_corporate, LocalDateTime generate_time) {
		this.id = id;
		this.is_corporate = is_corporate;
		this.generate_time = generate_time;
	}

	public String getId() {
		return id;
	}

	public boolean isIs_corporate() {
		return is_corporate;
	}

	public LocalDateTime getGenerate_time() {
		return generate_time;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
			"id='" + id + '\'' +
			", is_corporate=" + is_corporate +
			", generate_time=" + generate_time +
			'}';
	}
}
