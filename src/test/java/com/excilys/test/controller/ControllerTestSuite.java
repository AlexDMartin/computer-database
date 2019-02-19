package com.excilys.test.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   CliRendererTest.class,
   CreateComputerControllerTest.class,
   DeleteComputerControllerTest.class,
   ListCompanyControllerTest.class,
   ListComputerControllerTest.class,
   ShowDetailsControllerTest.class,
   UpdateComputerControllerTest.class,
   ValidatorTest.class
})

public class ControllerTestSuite {}
