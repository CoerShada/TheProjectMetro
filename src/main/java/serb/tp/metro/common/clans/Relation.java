package serb.tp.metro.common.clans;

import com.google.common.base.Throwables;

import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.DebugMessage;

public class Relation implements INBTSyncronized{
	private int id;
	private RelationType relationType = RelationType.NEUTRAL;
	private int[] clansId = new int[] {-1, -1};
	private int clanImprovingRelation = -1;
	
	public void setClansId(int[] clansId) {
		if (this.clansId[0]==-1 && this.clansId[1]==-1) {
			this.clansId = clansId;
		}	
	}
	
	public int[] getClansId() {
		return this.clansId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	public void improveRelations(int clanId) {

		if (clanImprovingRelation==-1) {
			clanImprovingRelation = clanId;
			return;
		}
		if (clanId != this.clanImprovingRelation) {
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
		clanImprovingRelation = -1;
		relationType = type;
	}

	public RelationType getType() {
		return relationType;
	}
	

	public int getImprooveRelations() {
		return clanImprovingRelation;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt = getNBT();
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.id = nbt.getInteger("id");
		this.clansId = nbt.getIntArray("clansId");
		this.clanImprovingRelation = nbt.getInteger("clanImprovingRelation");
		this.relationType = RelationType.valueOf(nbt.getString("relationType"));
		
	}

	@Override
	public NBTTagCompound getNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("id", this.id);
		tag.setIntArray("clansId", this.clansId);
		tag.setInteger("clanImprovingRelation", this.clanImprovingRelation);
		tag.setString("relationType", relationType.toString());
		return tag;
	}
}



