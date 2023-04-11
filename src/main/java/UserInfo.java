import java.time.LocalDateTime;

public class UserInfo {

	private String id;
	private String name;
	private boolean is_corporate;
	private LocalDateTime generate_time;

	public UserInfo() {
	}

	public UserInfo(String id, String name, boolean is_corporate, LocalDateTime generate_time) {
		this.id = id;
		this.name = name;
		this.is_corporate = is_corporate;
		this.generate_time = generate_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIs_corporate() {
		return is_corporate;
	}

	public void setIs_corporate(boolean is_corporate) {
		this.is_corporate = is_corporate;
	}

	public LocalDateTime getGenerate_time() {
		return generate_time;
	}

	public void setGenerate_time(LocalDateTime generate_time) {
		this.generate_time = generate_time;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", is_corporate=" + is_corporate +
				", generate_time=" + generate_time +
				'}';
	}
}
