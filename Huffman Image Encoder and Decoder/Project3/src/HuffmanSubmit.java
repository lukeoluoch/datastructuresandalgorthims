
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

import proj3.BinaryOut;
import proj3.Huffman;
import proj3.*;

import java.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;

// Import any package as required

public class HuffmanSubmit implements Huffman {
	static BinaryOut out;
	static BinaryIn in;

	// Feel free to add more methods and variables as required.
	private static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1');
		} else {
			st[x.character] = s;
		}
	}

	private static Node buildTree(int[] freq) {

		CustomPriorityQueue pq = new CustomPriorityQueue();
		for (char i = 0; i < 256; i++)
			if (freq[i] > 0)
				pq.add(new Node(null, null, i, freq[i]));

		if (pq.size() == 1) {
			if (freq['\0'] == 0)
				pq.add(new Node(null, null, '\0', 0));
			else
				pq.add(new Node(null, null, '\1', 0));
		}

		while (pq.size() > 1) {
			Node left = pq.Minimization();
			Node right = pq.Minimization();
			Node parent = new Node(left, right, '\0', left.frequency + right.frequency);
			pq.add(parent);
		}
		return pq.Minimization();
	}

	private static void writeTree(Node x) {
		if (x.isLeaf()) {
			out.write(true);
			out.write(x.character, 8);
			return;
		}
		out.write(false);
		writeTree(x.left);
		writeTree(x.right);
	}

	private static Node readTree() {
		boolean isLeaf = in.readBoolean();
		if (isLeaf) {
			return new Node(null, null, in.readChar(), -1);
		} else {
			return new Node(readTree(), readTree(), '\0', -1);
		}
	}

	public void encode(String inputFile, String outputFile, String freqFile) {
		String temp = "bytearray.txt";
		try {
			File imagepath = new File(inputFile);
			BufferedImage bi = ImageIO.read(imagepath);
			WritableRaster raster = bi.getRaster();
			DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
			byte[] array = data.getData();
			FileOutputStream f2 = new FileOutputStream(temp);
			f2.write(array);
			f2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		in = new BinaryIn(temp);
		out = new BinaryOut(outputFile);
		String s = in.readString();
		char[] input = s.toCharArray();

		int[] freq = new int[256];
		for (int i = 0; i < input.length; i++)
			freq[input[i]]++;
		
		Node root = buildTree(freq);
		
		HashMap<Integer,Integer> h = new HashMap<Integer,Integer>(); //Frequency File
        for(int i=0; i<freq.length; i++){
            if(h.containsKey(freq[i])){
                h.put(freq[i], h.get(freq[i]) + 1);
            } else {
                h.put(freq[i], 1);
            }
        }
        
        

        FileWriter fstream;
        BufferedWriter bw;

        
        try {
			fstream = new FileWriter(freqFile);
		
        bw = new BufferedWriter(fstream);

       
        Iterator<Entry<Integer, Integer>> it = h.entrySet().iterator();

   
        while (it.hasNext()) {

           
            Entry<Integer, Integer> pairs = it.next();

            bw.write(Integer.toBinaryString(pairs.getKey())+":"+pairs.getValue() + "            ");
            bw.write("\n");

            
        }
        
        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] st = new String[256];
		buildCode(st, root, "");
		writeTree(root);

		out.write(input.length);

		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '0') {
					out.write(false);
				} else if (code.charAt(j) == '1') {
					out.write(true);
				} else
					throw new IllegalStateException("Illegal state");
			}
		}

		out.close();

	}

	public void decode(String inputFile, String outputFile, String freqFile) {

		in = new BinaryIn(inputFile);
		out = new BinaryOut(outputFile);
		BinaryOut tempout = new BinaryOut("ur_dec.txt");

		Node root = readTree();

		int length = in.readInt();

		for (int i = 0; i < length; i++) {
			Node x = root;
			while (!x.isLeaf()) {
				boolean bit = in.readBoolean();
				if (bit)
					x = x.right;
				else
					x = x.left;
			}
			tempout.write(x.character, 8);
		}
		tempout.close();
		File file = new File("ur_dec.txt");
		byte[] array = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(array);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}

		System.out.println(array.length);
		try {
			InputStream in = new ByteArrayInputStream(array);

			BufferedImage image = ImageIO.read(new ByteArrayInputStream(array));
			RenderedImage rimage = (RenderedImage) image;
			ImageIO.write(rimage, "JPG", new File(outputFile));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException x) {
			x.getMessage();
			System.out.println("Despite trying his hardest and looking for solutions across the entirety of Google, the programmer could not figure out why the Illegal Argument Exception came up, despite him knowing the byte array before encoding and after decoding are identical. The image isn't null, yet the exception still occurs");
		}

	}

	public static void main(String[] args) throws IOException, FileNotFoundException {
		Huffman huffman = new HuffmanSubmit();

		// C:\Users\Luke Oluoch\Documents\Junior Year\Project3\src\alice.txt
		String loc1 = "src/ur.jpg";
		String loc2 = "src/ur.enc";
		String loc3 = "src/ur_dec.jpg";
		 huffman.encode(loc1, loc2, "freq.txt");
		System.out.println("Liiiiiiiiine");
		huffman.decode(loc2, loc3, "freq.txt");
		System.out.println("done :)");
		// After decoding, both ur.jpg and ur_dec.jpg should be the same.
		// On linux and mac, you can use `diff' command to check if they are the
		// same.
	}

}
