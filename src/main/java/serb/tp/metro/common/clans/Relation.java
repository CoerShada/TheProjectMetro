package serb.tp.metro.common.clans;

public class Relation {
	private int id;
	private RelationType relationType;
	private Clan[] clans = new Clan[2];
	private Clan clanImprovingRelation;
	
	public void improveRelations(Clan clan) {
		if (clanImprovingRelation==null) {
			clanImprovingRelation = clan;
			return;
		}
		if (!clan.equals(this.clanImprovingRelation)) {
			RelationType type;
			switch (relationType) {
				case WAR:
					type=RelationType.NEUTRAL;
					break;
				case NEUTRAL:
					type = RelationType.ALLIANCE;
					break;
				default:
					type=RelationType.NEUTRAL;
					break;
			}
			toDeclare(type);
		}
		
	}
	
	public void toDeclare(RelationType type) {
		if (relationType.equals(type)) return;
		clanImprovingRelation = null;
		relationType = type;
	}

	public RelationType getType() {
		return relationType;
	}
	
	public boolean isAThisClan(Clan clan) {
		return (clans[0] == clan || clans[1]==clan);
	}
	
	public Clan getImprooveRelations() {
		return clanImprovingRelation;
	}
}



