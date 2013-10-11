package net.thetabx.jmcgui.DataTypes;

public class MapData {
	
	private boolean isSet = false;
	private MapCoord coord;
	private byte[][][] data;
	private int lastY;
	private byte[][] topLayer;
	
	public MapData() { }
	
	// Data[x][z][y];
	public MapData(MapCoord coord, byte[][][] data, int lastY) {
		this.isSet = true;
		this.coord = coord;
		this.data = data;
		this.lastY = lastY;
	}
	
	public byte[][][] getData() {
		if(isSet)
			return data;
		return null;
	}
	
	public void setData(byte[][][] data) {
		this.data = data;
	}
	
	public MapCoord getMapCoord() {
		return coord;
	}
	
	public int getLastY() {
		return lastY;
	}
	
	public void setLastY(int lastY) {
		this.lastY = lastY;
	}
	
	public void calcTopLayer() {
		if(data == null)
			return;
		
		byte[][] topLayer = new byte[16][16];
		for(int iX = 0; iX < 16; iX++) {
			for(int iZ = 0; iZ < 16; iZ++) {
				for(int iY = 16 * lastY - 1; iY >= 0; iY--) {
					if(data[iX][iZ][iY] != 0) {
						topLayer[iX][iZ] = data[iX][iZ][iY];
						break;
					}
				}
			}
		}
		this.topLayer = topLayer;
	}
	
	public byte[][] getTopLayer() {
		if(topLayer == null)
			calcTopLayer();
		return topLayer;
	}
}
