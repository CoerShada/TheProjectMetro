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
				case War:
					type=RelationType.Neutral;
					break;
				case Neutral:
					type = RelationType.Alliance;
					break;
				default:
					type=RelationType.Neutral;
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


}



enum RelationType{
	War,
	Neutral,
	Alliance
}