package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferVO {
	private int transfer_id;
	private String player_position;
	private int transfer_year;
	private String fee;
	private PlayerVO player;
	private int age;
	private TeamVO previous_team;
	private TeamVO new_team;
}