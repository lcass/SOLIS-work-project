package com.Core.Util;

public class Logger {
	private static int maxlength;
	private int[] intlog;
	private String[] stringlog;
	private boolean intlogcleared = false;
	private boolean stringlogcleared = false;

	public Logger(int maxlength) {
		this.maxlength = maxlength;
		intlog = new int[maxlength];
		stringlog = new String[maxlength];
		for (int i = 0; i < maxlength; i++) {
			intlog[i] = internalcodes.EMPTYSLOT;
		}
	}

	public int getloggerstate() {
		if (maxlength <= 0) {
			return internalcodes.UNDEFINEDLENGTH;
		}
		if(intlog[0]== internalcodes.EMPTYSLOT && stringlog[0] == null){
			return internalcodes.NOLOGDATA;
		}
		if (intlog.length == maxlength - 1) {
			return internalcodes.INTLOGFULL;
		}
		if (stringlog.length == maxlength - 1) {
			return internalcodes.STRINGLOGFULL;
		} else {
			return internalcodes.NORMAL;
		}
	}

	public void printerror(String error) {
		System.err.println(error);
	}

	public void printerror(int error) {
		System.err.println(error);
	}

	public void dumplogs() {
		printerror("DUMPING LOGS");
		if (getloggerstate() != internalcodes.NORMAL) {
			printerror("WARNING ERROR CODE: " + getloggerstate());
			return;
		}
		for (int i = 0; i < maxlength; i++) {
			if (intlog[i] != internalcodes.EMPTYSLOT && !intlogcleared) {
				printerror(intlog[i]);
			} else if (!intlogcleared) {
				printerror("END OF INT DATA");
				intlogcleared = true;
			}
			if (stringlog[i] != null && !stringlogcleared) {
				printerror(stringlog[i]);
			} else if (!stringlogcleared) {
				printerror("END OF STRING DATA");
				stringlogcleared = true;
			}

		}
		stringlogcleared = false;
		intlogcleared = false;
		printerror("END OF LOGS, DATA CLEARED");
		for (int i = 0; i < maxlength; i++) {
			intlog[i] = internalcodes.EMPTYSLOT;
		}
		stringlog = new String[maxlength];
	}

	public void printlogs() {
		printerror("PRINTING LOGS");
		if (getloggerstate() == internalcodes.NOLOGDATA) {
			printerror("NO LOG DATA");
			return;
		}
		if (getloggerstate() != internalcodes.NORMAL) {
			printerror("WARNING ERROR CODE: " + getloggerstate());
			return;
		}
		for (int i = 0; i < maxlength; i++) {
			if (intlog[i] != internalcodes.EMPTYSLOT && !intlogcleared) {
				printerror(intlog[i]);
			} else if (!intlogcleared) {
				printerror("END OF INT DATA");
				intlogcleared = true;
			}
			if (stringlog[i] != null && !stringlogcleared) {
				printerror(stringlog[i]);
			} else if (!stringlogcleared) {
				printerror("END OF STRING DATA");
				stringlogcleared = true;
			}

		}
		stringlogcleared = false;
		intlogcleared = false;
	}

	public void clearlogs() {
		for (int i = 0; i < maxlength; i++) {
			intlog[i] = internalcodes.EMPTYSLOT;
		}
		stringlog = new String[maxlength];
	}

	public void log(String s) {
		for (int i = 0; i < maxlength; i++) {
			if (stringlog[i] == null) {
				stringlog[i] = s;
				break;
			}
		}
	}

	public void log(int i) {
		for (int s = 0; s < maxlength; s++) {
			if (intlog[i] == internalcodes.EMPTYSLOT) {
				intlog[i] = i;
				break;

			}
		}
	}
}
