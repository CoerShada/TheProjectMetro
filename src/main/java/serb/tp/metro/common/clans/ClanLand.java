package serb.tp.metro.common.clans;

public class ClanLand {
	
	private int id = 0;
	private int[] pos1 = new int[3];
	private int[] pos2 = new int[3];
	private String name;
	private ClanLand parent;
	private Clan owner;
	
	public ClanLand(int id, int[] pos1, int[] pos2, String name, ClanLand parent, Clan owner) {
		this.id = id;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.name = name;
		this.parent = parent;
		this.owner = owner;
		
	}
	
	public ClanLand(int id, int[] pos1, int[] pos2, String name) {
		this(id, pos1, pos2, name, null, null);
	}
	
	public int[] getPos1() {
		return pos1;
	}
	public void setPos1(int[] pos1) {
		this.pos1 = pos1;
	}
	
	public int[] getPos2() {
		return pos2;
	}
	public void setPos2(int[] pos2) {
		this.pos2 = pos2;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ClanLand getParent() {
		return parent;
	}
	
	public void setParent(ClanLand parent) {
		this.parent = parent;
	}
	
	public Clan getOwner() {
		return owner;
	}
	
	public void setOwner(Clan owner) {
		this.owner = owner;
	}
	
	
}
