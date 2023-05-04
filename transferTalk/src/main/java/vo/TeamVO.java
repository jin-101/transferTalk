package vo;

import java.util.Objects;

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
public class TeamVO {
	private int team_id;
	private String team_name;
	private LeagueVO league;
	private String team_img_src;
	@Override
	public int hashCode() {
		return Objects.hash(league.getLeague_name(), team_name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamVO other = (TeamVO) obj;
		return Objects.equals(league.getLeague_name(), other.league.getLeague_name())
				&& Objects.equals(team_name, other.team_name);
	}
	
}
