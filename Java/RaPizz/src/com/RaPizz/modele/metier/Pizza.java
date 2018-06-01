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
		private SimpleFloatProperty Prix;
		
		public Pizza(long ID_Pizza, String designation,float PrixBase)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.composition = new ArrayList<>();
			this.Photo = null;
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, List<Ingredient> composition)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = null;
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, Image photo)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.composition = new ArrayList<>();
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza(long ID_Pizza, String designation,float PrixBase, Image photo, List<Ingredient> composition)
		{
			this.ID_Pizza = ID_Pizza;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza( String designation,float PrixBase)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.composition = new ArrayList<>();
			this.Photo = null;
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza( String designation,float PrixBase, List<Ingredient> composition)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = null;
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza(String designation,float PrixBase, Image photo)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.composition = new ArrayList<>();
			this.Prix = new SimpleFloatProperty();
		}
		public Pizza( String designation,float PrixBase, Image photo, List<Ingredient> composition)
		{
			this.ID_Pizza = 0;
			this.designation = designation;
			this.composition = composition;
			this.PrixBase = PrixBase;
			this.Photo = photo;
			this.Prix = new SimpleFloatProperty();
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
			return Prix;
		}
		public void setPrix(SimpleFloatProperty prix) {
			Prix = prix;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (ID_Pizza ^ (ID_Pizza >>> 32));
			result = prime * result + ((Prix == null) ? 0 : Prix.hashCode());
			result = prime * result + Float.floatToIntBits(PrixBase);
			result = prime * result + ((composition == null) ? 0 : composition.hashCode());
			result = prime * result + ((designation == null) ? 0 : designation.hashCode());
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
			if (Prix == null) {
				if (other.Prix != null)
					return false;
			} else if (!Prix.equals(other.Prix))
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
			return true;
		}
		
}
