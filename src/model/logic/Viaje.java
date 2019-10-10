
package model.logic;

public class Viaje implements Comparable<Viaje>
{
	//Identificador único de la zona de origen de los viajes relacionados
	public int sourceid;

	// Identificador único de la zona de destino de los viajes relacionados
	public int dstid;

	//número del mes de los viajes relacionados
	public int hourDayMonth;

	//tiempo promedio en segundos de los viajes relacionados
	public float mean_travel_time;

	//desviación estándar de los viajes relacionados
	public float standard_deviation_travel_time;

	//desviación estándar de los viajes relacionados
	public float geometric_mean_travel_time;

	//desviación estándar geométrica de los viajes relacionados
	public float geometric_standard_deviation_travel_time;

	public Viaje(int pSourceid, int pDstid, int pHourDaymonth,   float pMean_travel_time, float pStandard_deviation_travel_time, float pGeometric_mean_travel_time, float pGeometric_standard_deviation_travel_time )
	{
		sourceid = pSourceid;
		dstid = pDstid;
		hourDayMonth=pHourDaymonth;
		mean_travel_time = pMean_travel_time;
		standard_deviation_travel_time = pStandard_deviation_travel_time;
		geometric_mean_travel_time = pGeometric_mean_travel_time;
		geometric_standard_deviation_travel_time =pGeometric_standard_deviation_travel_time;

	}

	public int getSourceid() {
		return sourceid;
	}

	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}

	public int getDstid() {
		return dstid;
	}

	public void setDstid(int dstid) {
		this.dstid = dstid;
	}

	public int getHourDayMonth() {
		return hourDayMonth;
	}

	public void setDayHourMonth(int month) {
		this.hourDayMonth = month;
	}


	public float getMean_travel_time() {
		return mean_travel_time;
	}

	public void setMean_travel_time(float mean_travel_time) {
		this.mean_travel_time = mean_travel_time;
	}

	public float getStandard_deviation_travel_time() {
		return standard_deviation_travel_time;
	}

	public void setStandard_deviation_travel_time(float standard_deviation_travel_time) {
		this.standard_deviation_travel_time = standard_deviation_travel_time;
	}

	public float getGeometric_mean_travel_time() {
		return geometric_mean_travel_time;
	}

	public void setGeometric_mean_travel_time(float geometric_mean_travel_time) {
		this.geometric_mean_travel_time = geometric_mean_travel_time;
	}

	public float getGeometric_standard_deviation_travel_time() {
		return geometric_standard_deviation_travel_time;
	}

	public void setGeometric_standard_deviation_travel_time(float geometric_standard_deviation_travel_time) {
		this.geometric_standard_deviation_travel_time = geometric_standard_deviation_travel_time;
	}

	//sourceid: Identificador único de la zona de origen de los viajes relacionados
	// dstid: Identificador único de la zona de destino de los viajes relacionados


	public int compareTo( Viaje comparador) 
	{
		if(sourceid == comparador.getSourceid())
		{
			if(dstid==comparador.getDstid())
				return 0;
			else if	(dstid<comparador.getDstid())
				return -1;
			else
				return 1;
		}
		else if(sourceid<comparador.getSourceid())
		{
			return -1;
		}
		else
			return 1;
	}

}

