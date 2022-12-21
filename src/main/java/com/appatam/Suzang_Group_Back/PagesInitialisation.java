package com.appatam.Suzang_Group_Back;

import com.appatam.Suzang_Group_Back.domain.AccueilPage;
import com.appatam.Suzang_Group_Back.domain.AtoutPage;
import com.appatam.Suzang_Group_Back.domain.ClientPage;
import com.appatam.Suzang_Group_Back.domain.ContactPage;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.domain.ServicePage;
import com.appatam.Suzang_Group_Back.service.IAccueilPageService;
import com.appatam.Suzang_Group_Back.service.IAtoutPageSevice;
import com.appatam.Suzang_Group_Back.service.IClientPageService;
import com.appatam.Suzang_Group_Back.service.IContactPageService;
import com.appatam.Suzang_Group_Back.service.IPresentationPageService;
import com.appatam.Suzang_Group_Back.service.IServicePageService;

public class PagesInitialisation {
	
	
	public void initializePage(IPresentationPageService IpService
			  ,IClientPageService IcService,IAtoutPageSevice iaService,IServicePageService IsService,
			  IAccueilPageService iaccueilService,IContactPageService icontactsevice) {
		
		if(IpService.ListePresentation().isEmpty()) { 
			
			String titre1="Des chasseurs relâchés dans la ville";
			String contenu1="Nous sommes SUZANG GROUP, nous sortons du bois sacré. Une armée de chasseurs initiés aux \r\n"
					+ "arts de la guérilla urbaine. Nous chassons le consommateur, traquons ses attentes, reniflons ses \r\n"
					+ "besoins. Depuis 2014, nous pistons les tendances des marchés, pour accrocher le succès à votre \r\n"
					+ "tableau de chasse. \r\n"
					+"\"Si nous traquons le gibier,c’est pour l’accrocher à votre tableau.\"";
			
			IpService.enregistrerPresentation(new PresentationPage(titre1, contenu1));
		}; 
		
		if(IcService.ListeClientPage().isEmpty()) { 
			
			String titre1="Le meilleur, rien de moins";
			String contenu1="Le terrain est impitoyable, tout comme le bois sacré. Nous avons développé la culture du courage. \r\n"
					+ "Y compris le courage de se remettre en cause. \r\n"
					+ "Nous manions la confidentialité comme une stratégie à part entière au même titre que la publicité. \r\n"
					+ "Car en matière de chasse, la surprise est un art qui permet de prendre avantage.\r\n"
					+ "Nous visons l’excellence. Nous débusquons les meilleures technologies, pourchassons les meilleures solutions pour apporter les réponses les plus adaptées aux défis que vous affrontez.\r\n"
					+ "Sur tous les canaux, nous planifions, préparons et organisons minutieusement cette rencontre \r\n"
					+ "entre vous et votre client.";
					
			IcService.enregistrerClientPage(new ClientPage(titre1, contenu1));
		}
		
		if(iaService.ListeAtoutPage().isEmpty()) {
			
			String titre1="UNE INITIATION, DEUX MAÎTRES";
			String contenu1="Tout initié a un maître. \r\n"
					+ "Nous en avons deux. \r\n"
					+ "SYNERGIE et INNOVATION nous communiquent force et état d’esprit. \r\n"
					+ "INNOVATION nous enseigne que le gibier \r\n"
					+ "ne se fait jamais prendre deux fois au même \r\n"
					+ "endroit. Nous nous devons de nous renouveler sans cesse, ouvrir des voies nouvelles, \r\n"
					+ "surprendre en permanence.";
			String contenu2="SYNERGIE nous enseigne que la solitude programme \r\n"
					+ "l’échec. Pour réussir nous devons à la fois travailler en \r\n"
					+ "harmonie et travailler l’harmonie. Que ce soit entre collaborateurs, avec le partenaire, ou au sein de l’environnement, nous devons toujours trouver le moyen de \r\n"
					+ "donner, d’échanger, de fusionner, pour profiter de l’effet de décuplement des énergies.\r\n"
					+ "Des combinaisons de ces vertus sortent les offres les \r\n"
					+ "plus prometteuses, les plus surprenantes, surtout celles \r\n"
					+ "qui comptent et tiennent compte des réalités et des besoins du marché.";
					
			iaService.enregistrerAtoutPage(new AtoutPage(titre1, contenu1,contenu2));
		}
		
		if(IsService.ListeServicePage().isEmpty()) { 
			
			String titre1="Le meilleur, rien de moins";
			String contenu1="Le terrain est impitoyable, tout comme le bois sacré. Nous avons développé la culture du courage. \r\n"
					+ "Y compris le courage de se remettre en cause.";
					
			IsService.enregistrerServicePage(new ServicePage(titre1, contenu1));
		}
      if(iaccueilService.ListeAccueilPage().isEmpty()) { 
			
			String titre11="Des chasseurs relachés dans la ville";
			String contenu11="Nous sommes SUZANG GROUP, nous sortons du bois sacré. Une armée de chasseurs initiés aux \r\n"
					+ "arts de la guérilla urbaine. Nous chassons le consommateur, traquons ses attentes, reniflons ses \r\n"
					+ "besoins. Depuis 2014, nous pistons les tendances des marchés, pour accrocher le succès à votre \r\n"
					+ "tableau de chasse. \r\n"
					+ "\"Si nous traquons le gibier,c’est pour l’accrocher à votre tableau.\"";
			
			String titre22="Une initiation, deux maitres";
			String contenu21="Tout initié a un maître. \r\n"
					+ "Nous en avons deux. \r\n"
					+ "SYNERGIE et INNOVATION nous communiquent force et état d’esprit. \r\n"
					+ "INNOVATION nous enseigne que le gibier \r\n"
					+ "ne se fait jamais prendre deux fois au même \r\n"
					+ "endroit. Nous nous devons de nous renouveler sans cesse, ouvrir des voies nouvelles, \r\n"
					+ "surprendre en permanence.";
			String contenu22="SYNERGIE nous enseigne que la solitude programme \r\n"
					+ "l’échec. Pour réussir nous devons à la fois travailler en \r\n"
					+ "harmonie et travailler l’harmonie. Que ce soit entre collaborateurs, avec le partenaire, ou au sein de l’environnement, nous devons toujours trouver le moyen de \r\n"
					+ "donner, d’échanger, de fusionner, pour profiter de l’effet de décuplement des énergies.\r\n"
					+ "Des combinaisons de ces vertus sortent les offres les \r\n"
					+ "plus prometteuses, les plus surprenantes, surtout celles \r\n"
					+ "qui comptent et tiennent compte des réalités et des besoins du marché.";
			
			String titre33="LE MEILLEUR, RIEN DE MOINS";
			String contenu31="Le terrain est impitoyable, tout comme le bois sacré. Nous avons développé la culture du courage. \r\n"
					+ "Y compris le courage de se remettre en cause. \r\n"
					+ "Nous manions la confidentialité comme une stratégie à part entière au même titre que la publicité. \r\n"
					+ "Car en matière de chasse, la surprise est un art qui permet de prendre avantage.\r\n"
					+ "Nous visons l’excellence. Nous débusquons les meilleures technologies, pourchassons les meilleures solutions pour apporter les réponses les plus adaptées aux défis que vous affrontez. ";
			String contenu32="Sur tous les canaux, nous planifions, préparons et organisons minutieusement cette rencontre \r\n"
					+ "entre vous et votre client.";
					
			iaccueilService.enregistrerAccueilPage(new AccueilPage(titre11, contenu11,0,0,titre22,contenu21,contenu22 ,0,0,titre33,contenu31,contenu32,0,0));
		}
      
      if(icontactsevice.ListeContactPage().isEmpty()) { 
			
		
					
			icontactsevice.enregistrerContactPage(new ContactPage("ADRESSE", "Villa 69 Cité Belle Vue,\n"
					+ "                         Cocody Angré 8ieme Tranche","TELEPHONE","Tel: (225)22421483 / (225)07\n"
							+ "                          77636140/ (225)0777636307","EMAIL","info@suzang-group.com"));
		}
	}

}
