package main;

import java.io.File;
import java.util.Scanner;

import util.validator.ValidatorUtil;

public class stepCountMain {

	public static void main(String args[]) {
		System.out.println("--> ------------------------------------------------");
		System.out.println("--> カウント対象のフォルダパスを入力してください");
		System.out.println("--> ------------------------------------------------");
		@SuppressWarnings("resource")
		final Scanner sn = new Scanner(System.in);
		final String inputFilePath = sn.next();
		if (!ValidatorUtil.inputFileCheck(inputFilePath)) {
			System.out.println("--> [INFO]:処理を終了します");
			return;
		}
		System.out.println("--> ------------------------------------------------");
		System.out.println("--> カウント結果出力対象のファイルをパスを入力してください");
		System.out.println("--> ファイル拡張子：CSV");
		System.out.println("--> ------------------------------------------------");
		final String outputFilePath = sn.next();
		if (!ValidatorUtil.inputFileCheck(outputFilePath)) {
			System.out.println("--> [INFO]:処理を終了します");
			return;
		}
		File inputFile = new File(inputFilePath);
		File outputFile = new File(outputFilePath);
		try {
			System.out.println("--> [INFO]:ステップカウント処理を開始します");
			writeHeaderStepCount(outputFile);
			processDirectory(inputFile, outputFile);
			System.out.println("--> [INFO]:ステップカウント処理を終了します");
		} catch (Exception e) {
			System.out.println("--> [INFO]:ステップカウント処理で異常が発生しました。処理を終了します。");
			return;
		}	
	}
	private static void writeHeaderStepCount(File outputfile) {
		
	}
	private static void processDirectory(File dir, File outputFile) {
        File[] files = dir.listFiles();

        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                processDirectory(f,outputFile);
            } else if (f.isFile()) {
            }
        }
    }
    
}
