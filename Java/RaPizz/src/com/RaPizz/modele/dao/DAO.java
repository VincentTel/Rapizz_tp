package com.RaPizz.modele.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class DAO {
	protected Connection connection;
	
	public DAO(Connection con)
	{
		connection = con;
	}

	public static InputStream ConvertImage(Image i)
	{
		BufferedImage bImage = SwingFXUtils.fromFXImage(i, null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = null;
		try {
		    ImageIO.write(bImage, "png", outputStream);
		    byte[] res  = outputStream.toByteArray();
		    inputStream = new ByteArrayInputStream(res);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return inputStream;
	}
}
