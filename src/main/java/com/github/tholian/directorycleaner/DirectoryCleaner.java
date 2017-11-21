package com.github.tholian.directorycleaner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryCleaner {
	private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryCleaner.class);

	private Options options;

	DirectoryCleaner() {
		// hide constructor
		options = new Options();
		options.addOption(Option.builder("h").desc("print this message").longOpt("help").build());

		options.addOption(Option.builder("d").desc("root directory").hasArg().longOpt("directory").type(File.class)
				.required().build());
		options.addOption(Option.builder("dry").desc("does not really remove anything").longOpt("dryrun").build());
		// options.addOption(Option.builder("file").desc("configuration
		// file").hasArg().longOpt("configurationfile").build());
		options.addOption(Option.builder("age").desc("older than this days").type(Integer.class).build());
		options.addOption(Option.builder("include").desc("RegEx directory name included").build());
		options.addOption(Option.builder("exclude").desc("RegEx directory name excluded").build());
	}

	protected void doMain(String[] args) throws IOException {
		CommandLineParser parser = new DefaultParser();
		try {
			LOGGER.debug("Parsing arguments!");
			CommandLine cmd = parser.parse(options, args);
			LOGGER.debug("Parsing of arguments finished");

			File rootDirectory = (File) cmd.getOptionObject('d');

			LOGGER.debug("Root directory is '{}'", rootDirectory);

			DirectoryCleanerDirectoryWalker walker = new DirectoryCleanerDirectoryWalker(DirectoryFileFilter.INSTANCE,-1) ;			
			
			Collection walkingResult = walker.go(rootDirectory);

			LOGGER.debug("Result of walker: {}", walkingResult);

		} catch (ParseException e) {
			LOGGER.debug("Error parsing arguments.", e);
			printHelp();
		}

	}

	private void printHelp() {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("directory cleaner", options);
	}

	public static void main(String[] args) {

		LOGGER.info("Starting Application directory cleaner!");
		DirectoryCleaner directoryCleaner = new DirectoryCleaner();
		try {
			directoryCleaner.doMain(args);
		} catch (IOException e) {
			LOGGER.error("Error running directory cleaner.", e);
		}

		LOGGER.info("Main method of directory cleaner finished!");

	}
}
