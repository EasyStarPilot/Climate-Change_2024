
public class Mars {
    public static void main(String[] args) {
         
        // Definition von Konstanten in SI-Einheiten:
        
        double sigma = 5.67e-8;                    // Stefan-Boltzmann-Konstante in W/(m^2 K^4)
         double E_0 = 589.2;                         // Solarkonstante in W/m^2
         double R_E = 3396200;                      // Marsradius in m
         double C = 1.37e23;                        // Waermekapazitaet des Marsklimasystems in J/K
         double alpha_S = 0.250 ;                    // Sphaerischer Albedo der Mars
         double P_Marsinneres = 0;                    // Leistung aus Marsinnerem in W
         double Jahr = 365.24 * 24 * 3600;          // Jahr in s
         
          // Abgeleitete Konstanten:
          
         double Q_E = Math.PI * R_E *  R_E;         // Querschnittsflaeche der Mars in m^2
         double A_E = 4 * Math.PI * R_E *  R_E;     // Oberflaeche der Mars in m^2
         double P_Einstrahlung = Q_E * E_0;         // Leistung aus Sonneneinstrahlung in W
         
         // Parameter:
         
         double T = 273.15 + 14.0;                  // Anfangstemperatur der Marsoberflaeche in K
         double t = 2024 * Jahr;                    // Anfangsjahr in s
         double Delta_t = 1.0 * Jahr;               // Zeitschritt der Simulation in s
         double N = 100;                            // Anzahl der Iterationen
         
        for (int j = 0; j < N; j++) {
            double P_aus = sigma * A_E * T * T * T * T;                                     //Ausstrahlung Mars
            double Delta_E = (P_Einstrahlung*(1-alpha_S) + P_Marsinneres - P_aus) * Delta_t; //Aufzunehmende Energie
            double Delta_T = Delta_E / C;                                                   //Einstrahlung/Wärmekapatzität
            T = T + Delta_T;                                                                //Marsoberflächentemperatur
            t = t + Delta_t;                                                                //Jetziges Jahr
            System.out.println("Jahr: " + t/Jahr + " Temperatur: " + (T - 273.15));         //Ausgabe
        }
                 
    }
     
}
