package com.mg153.RapidWinesListFr;

public class Vin {

	private int id;
	private String nom;
	private String maison;
	private String pays;
	private String region;
	private String couleur;
	private String mille;
	private String alcool;
	private String prix;
	private String code;
	private String note;
	private String apprec;
    private int imageId; 
    private String imagePath; 

        public Vin(int imageid, String nom, String region, String pays, String prix) {
         this.imageId = imageid;
         this.nom = nom;
         this.region = region;
         this.pays = pays;
         this.prix = prix;     } 

        public int getimageid() {
         return imageId;     }

        public void setimageid(int imageid) {
         this.imageId = imageid;     } 

	public String getimagepath() {
		return this.imagePath;
	}

	public void setimagepath(String imagepath) {
		this.imagePath = imagepath;
	}


	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getnom() {
		return this.nom;
	}

	public void setnom(String nom) {
		this.nom = nom;
	}

	public String getmaison() {
		return this.maison;
	}

	public void setmaison(String maison) {
		this.maison = maison;
	}

	public String getpays() {
		return this.pays;
	}

	public void setpays(String pays) {
		this.pays = pays;
	}
	   
	public String getregion() {
		return this.region;
	}

	public void setregion(String region) {
		this.region = region;
	}
	
	public String getcouleur() {
		return this.couleur;
	}

	public void setcouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public String getmille() {
		return this.mille;
	}

	public void setmille(String mille) {
		this.mille = mille;
	}
	
	public String getalcool() {
		return this.alcool;
	}

	public void setalcool(String alcool) {
		this.alcool = alcool;
	}
	
	public String getprix() {
		return this.prix;
	}

	public void setprix(String prix) {
		this.prix = prix;
	}
	
	public String getcode() {
		return this.code;
	}

	public void setcode(String code) {
		this.code = code;
	}
	   
	
	public String getnote() {
		return this.note;
	}

	public void setnote(String note) {
		this.note = note;
	}
	
	public String getapprec() {
		return this.apprec;
	}

	public void setapprec(String apprec) {
		this.apprec = apprec;
	}
	
	
	@Override
	public String toString() {
		return nom + "    " + prix; 
	}
	
}
