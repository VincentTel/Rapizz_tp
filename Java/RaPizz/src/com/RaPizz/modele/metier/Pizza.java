package com.RaPizz.modele.metier;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.image.Image;

public class Pizza {
		private List<Ingredient> composition;
		private long ID_Pizza;
		private String designation;
		private float PrixBase;
		private Image Photo;
		private Taille size = null;
		private SimpleFloatProperty prixTotal= new SimpleFloatProperty();
		
		public Pizza(long ID_Pizza, String designation,float PrixBase)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.composition = new ArrayList<>();
			this.Photo = null;
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, List<Ingredient> composition)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = null;
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, Image photo)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.composition = new ArrayList<>();
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, Image photo, List<Ingredient> composition)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = photo;
		}
		public Pizza( String designation,float PrixBase)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.composition = new ArrayList<>();
			this.Photo = null;
		}
		public Pizza( String designation,float PrixBase, List<Ingredient> composition)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = null;
		}
		public Pizza(String designation,float PrixBase, Image photo)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.composition = new ArrayList<>();
		}
		public Pizza( String designation,float PrixBase, Image photo, List<Ingredient> composition)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = photo;
		}
		public Pizza(Pizza p)
		{
			this.ID_Pizza = p.getID_Pizza();
			this.designation = p.getDesignation();
			this.composition = p.getComposition();
			this.PrixBase = p.getPrixBase();
			this.Photo = p.getPhoto();
			this.size = p.getSize();
			this.prixTotal = p.getPrix();
			
		}
		public List<Ingredient> getComposition() {
			return composition;
		}
		public void setComposition(List<Ingredient> composition) {
			this.composition = composition;
		}
		public long getID_Pizza() {
			return ID_Pizza;
		}
		public void setID_Pizza(long ID_Pizza) {
			this.ID_Pizza = ID_Pizza;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public Image getPhoto() {
			return Photo;
		}
		public void setPhoto(Image photo) {
			Photo = photo;
		}
		public float getPrixBase() {
			return PrixBase;
		}
		public void setPrixBase(float prixBase) {
			PrixBase = prixBase;
		}	
		public SimpleFloatProperty getPrix() {						
			return prixTotal;
		}		
		public Taille getSize() {
			return size;
		}
		public void setSize(Taille size) {
			if(size!=null)
				prixTotal.setValue( PrixBase * size.getPrixPonderation());		
			else 
				prixTotal.setValue(0);
			
			this.size = size;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (ID_Pizza ^ (ID_Pizza >>> 32));
			result = prime * result + Float.floatToIntBits(PrixBase);
			result = prime * result + ((composition == null) ? 0 : composition.hashCode());
			result = prime * result + ((designation == null) ? 0 : designation.hashCode());
			result = prime * result + ((size == null) ? 0 : size.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pizza other = (Pizza) obj;
			if (ID_Pizza != other.ID_Pizza)
				return false;
			if (Float.floatToIntBits(PrixBase) != Float.floatToIntBits(other.PrixBase))
				return false;
			if (composition == null) {
				if (other.composition != null)
					return false;
			} else if (!composition.equals(other.composition))
				return false;
			if (designation == null) {
				if (other.designation != null)
					return false;
			} else if (!designation.equals(other.designation))
				return false;
			if (size == null) {
				if (other.size != null)
					return false;
			} else if (!size.equals(other.size))
				return false;
			return true;
		}
	
	
		
		
}
