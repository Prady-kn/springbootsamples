package com.example.stringtemplate;

import java.io.IOException;
import java.util.*;
import java.io.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.stringtemplate.v4.ST;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.FMParser;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static Object put;

	public static void main(final String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	public static void StringTemplateDemo() {
		// StringTemplate
		final ST hello = new ST("Hello, <name>!");
		hello.add("name", "World");
		final String output = hello.render();
		System.out.println(output);
	}

	public static void FreeMakerDemo() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		final Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		final StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("greetTemplate", "<#macro greet>Hello ${user}!!!</#macro>");
		stringLoader.putTemplate("myTemplate", "<#include \"greetTemplate\"><@greet/> World!");
		cfg.setTemplateLoader(stringLoader);

		final Template tmpl = cfg.getTemplate("myTemplate");
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("user", "pradeep");

		StringWriter out =new StringWriter();
	//	Writer out = new OutputStreamWriter(System.out);

		tmpl.process(root, out);

		System.out.println(out.toString());

	}

	@Override
	public void run(final String... args) throws Exception {
		// TODO Auto-generated method stub
		StringTemplateDemo();
		FreeMakerDemo();
	}

}
