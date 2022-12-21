package com.appatam.Suzang_Group_Back.Commun;

import static java.util.Objects.nonNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.appatam.Suzang_Group_Back.domain.Visiteurs;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import lombok.RequiredArgsConstructor;


public class ClientReferences {
	
	
	private DatabaseReader databaseReader;
	
	public ClientReferences(DatabaseReader databaseReader) {
        this.databaseReader = databaseReader;
    }  	
	
	public ClientReferences() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getClientIP(HttpServletRequest request) {

		final String LOCALHOST_IPV4 = "127.0.0.1";
		final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
		
		String	ipAddress = request.getRemoteAddr();
		if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
			try {
				
				ipAddress ="160.120.230.82";		    		
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		return ipAddress;
	}
	

	public Visiteurs getViseteursLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {

		Visiteurs visiteurs = new Visiteurs();
		
		String ip =  getClientIP(request);

        InetAddress ipAddress = InetAddress.getByName(ip); 	
        
        CityResponse cityResponse = databaseReader.city(ipAddress);
        
        InetAddress LocalAddress = InetAddress.getLocalHost();
        
        if (nonNull(cityResponse) && nonNull(cityResponse.getCity())) {

            String continent =  (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            
            String country =  (cityResponse.getCountry() != null) ? cityResponse.getCountry().getName() : "";
            
            String city = cityResponse.getCity().getName();
            
            Double latitude = cityResponse.getLocation().getLatitude();
            
            Double longitude =  cityResponse.getLocation().getLongitude();
            
            String localAddress=LocalAddress.getHostAddress();
            
            String localHostName=LocalAddress.getHostName();
            
            String[] arr = request.getRequestURI().split("/", 0);  
            
            visiteurs.setIpAddress(ip);
            visiteurs.setPays(country);
            visiteurs.setVille(city);
            visiteurs.setLat(latitude);
            visiteurs.setLon(longitude);
            visiteurs.setHostIpAddress(localAddress);
            visiteurs.setHostName(localHostName);
            visiteurs.setPageVisite(arr[2]);
 
        }
		return visiteurs; 
      
    }
	
	
	
}
