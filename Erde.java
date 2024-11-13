public class Erde {    
    public static void main(String[] args) {

        // Definition von Konstanten in SI-Einheiten:        
        double sigma = 5.67e-8;			// Stefan-Boltzmann-Konstante in W/(m^2 K^4) 
        double E_0 = 1361;				// Solarkonstante in W/m^2
        double R_E = 6371000;			// Erdradius in m        
        double C = 1.37e23;				// Waermekapazitaet des Erdklimasystems in J/K
        double alpha_S = 0.306;			// Sphaerischer Albedo der Erde
        double P_Erdinneres = 4.7e13;		// Leistung aus Erdinnerem in W        
        double Jahr = 365.24 * 24 * 3600;		// Jahr in s        

        // Abgeleitete Konstanten:        
        double Q_E = Math.PI * R_E *  R_E;	// Querschnittsflaeche der Erde in m^2        
        double A_E = 4 * Math.PI * R_E *  R_E;	// Oberflaeche der Erde in m^2        
        double P_Einstrahlung_vorher = Q_E * E_0;  // Leistung aus Sonneneinstrahlung in W        

        // Parameter:        
        double T = 273.15 + 14.0;			// Anfangstemperatur der Erdoberflaeche in K 
        double t = 2024 * Jahr;			// Anfangsjahr in s        
        double Delta_t = 1.0 * Jahr;			// Zeitschritt der Simulation in s        
        double N = 100;				// Anzahl der Iterationen        
        double ɛ = 0.78;        
        int szenario = 2;				// 1: optimistisch, 2: realistisch, 3: pessimistisch        
        double block = 50.0;				// Prozentuale Undurchlässigkeit des Schattens        
        double s = 7.5;				// Ziel-Erdquerschnittsbeschattung
        double s_Wachstum = 0.5;			// Jährliche Beschattungszuwachsrate     
        double s_aktuell = 0.0;			// Initialwert der Beschattung

        // Berechnung pro Jahr:
        for (int j = 0; j < N; j++) {            
            double ty = t/Jahr;            
            double CO2_Konzentration = 0.; 

            if (s_aktuell < s) {
                s_aktuell = s_aktuell + s_Wachstum;}            
            if (s_aktuell >= s) {
                s_aktuell = s;}                        
            double P_Einstrahlung = P_Einstrahlung_vorher; //* (1 - s_aktuell/100.0 * block/100.0); 
            //Schatten weggerechnet            

            if (szenario == 3) {						//SzenarioCO2konzentration
                CO2_Konzentration = 7.6315789473684 * ty - 15026;} 
            else if (szenario == 2) {
                CO2_Konzentration = 5 * ty - 9699.68421;}            
            else if (szenario == 1) {
                CO2_Konzentration = 2.3684210526316 * ty - 4373; }            

            ɛ = 0.0000927 * CO2_Konzentration + 0.742;            
            double P_aus = sigma * A_E * T * T * T * T ;		//Ausstrahlung Erde 
            double P_aus1 = P_aus * ( 1 - ɛ );            
            double P_aus2 = ( P_aus - P_aus1 ) / 2;             
            P_aus = P_aus1 + P_aus2;            
            double Delta_E = ((P_Einstrahlung)*(1-alpha_S) + P_Erdinneres - P_aus) * Delta_t; 										//Aufzunehmende Energie            
            double Delta_T = Delta_E / C;				//Einstrahlung/ Waermekapatzität            
            T = T + Delta_T; 						//Erdoberflächentemperatur            
            t = t + Delta_t;						//Jetziges Jahr            
            System.out.println("Jahr: " + t/Jahr + " Temperatur: " + (T - 273.15));
            //Ausgabe
        }}}
