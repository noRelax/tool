package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.lang.ArrayUtils;

public class Pcm2Wav {
	public static void main(String[] args) throws Exception {
//    	System.out.println(System.nanoTime());
//    	System.out.println(System.currentTimeMillis());
		parseMulError("C:\\Users\\gang\\Desktop\\jars\\sound\\error.wav", "C:\\Users\\gang\\Desktop\\jars\\sound\\1.pcm");
		parseOK("C:\\Users\\gang\\Desktop\\jars\\sound\\ok.wav", "C:\\Users\\gang\\Desktop\\jars\\sound\\1.pcm");
	}

	public static void parse(String source, String target) throws Exception {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		File sourceFile = new File(source);
		FileOutputStream out = new FileOutputStream(new File(target));
		AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(sourceFile), af,
				sourceFile.length());
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
		audioInputStream.close();
		out.flush();
		out.close();
	}

	public static void parseMul(String target, String... sources) throws Exception {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		FileOutputStream out = new FileOutputStream(new File(target));
		for (int i = 0; i < sources.length; i++) {
			String source = sources[i];
			File sourceFile = new File(source);

			AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(sourceFile), af,
					sourceFile.length());
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
			audioInputStream.close();
		}

		out.flush();
		out.close();
	}

	public static void parseMulError(String target, String source) throws Exception {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		FileOutputStream out = new FileOutputStream(new File(target));
		for (int i = 0; i < 3; i++) {
			File sourceFile = new File(source);

			AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(sourceFile), af,
					sourceFile.length());
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
			audioInputStream.close();
		}

		out.flush();
		out.close();
	}

	public static void parseMul3(String target, String source) throws Exception {
		String source2 = "C:\\Users\\gang\\Desktop\\jars\\sound\\1.txt";

		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		FileOutputStream out = new FileOutputStream(new File(target), true);

		Vector<InputStream> v = new Vector<>();

		File sourceFile = new File(source);
		v.addElement(new FileInputStream(sourceFile));
		File sourceFile2 = new File(source2);
		v.addElement(new FileInputStream(sourceFile2));

		SequenceInputStream se = new SequenceInputStream(new FileInputStream(sourceFile),
				new FileInputStream(sourceFile2));
		System.out.println("se.available():" + se.available());
		byte[] byt = new byte[se.available()];
		System.out.println("byt.length:" + byt.length);
		se.read(byt);
		se.close();
		InputStream totalInput = new ByteArrayInputStream(byt);

		AudioInputStream audioInputStream = new AudioInputStream(totalInput, af, byt.length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
		audioInputStream.close();

		out.flush();
		out.close();
	}

	public static void parseMul1(String target, String source) throws Exception {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		FileOutputStream out = new FileOutputStream(new File(target), true);

		File sourceFile = new File(source);

		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			InputStream input = new FileInputStream(sourceFile);
			byte[] byt = new byte[input.available()];
			input.read(byt);
			input.close();
			for (int j = 0; j < byt.length; j++) {
				list.add(byt[j]);
			}
		}
		byte[] totalByte = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			totalByte[i] = list.get(i);
		}

		InputStream totalInput = new ByteArrayInputStream(totalByte);

		AudioInputStream audioInputStream = new AudioInputStream(totalInput, af, totalByte.length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);

		out.close();
	}

	public static void parseOK(String target, String source) throws Exception {
		float sampleRate = 16000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		FileOutputStream out = new FileOutputStream(new File(target), true);

		File sourceFile = new File(source);

		byte[] totalByte = new byte[0];
		for (int i = 0; i < 3; i++) {
			InputStream input = new FileInputStream(sourceFile);
			byte[] byt = new byte[input.available()];
			input.read(byt);
			input.close();
			totalByte = ArrayUtils.addAll(totalByte, byt);
		}

		InputStream totalInput = new ByteArrayInputStream(totalByte);

		AudioInputStream audioInputStream = new AudioInputStream(totalInput, af, totalByte.length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);

		out.close();
	}

}