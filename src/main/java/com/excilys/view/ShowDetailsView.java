package com.excilys.view;

import com.excilys.dto.ComputerDTO;

/**
 * The Class ShowDetailsView.
 */
public class ShowDetailsView {

  /** The show details view instance. */
  private static ShowDetailsView showDetailsViewInstance = null;

  /**
   * Instantiates a new show details view.
   */
  private ShowDetailsView() {
  }

  /**
   * Gets the single instance of ShowDetailsView.
   *
   * @return single instance of ShowDetailsView
   */
  public static ShowDetailsView getInstance() {
    if (showDetailsViewInstance == null) {
      showDetailsViewInstance = new ShowDetailsView();
    }
    return showDetailsViewInstance;
  }

  /**
   * Ask for id.
   */
  public void askForId() {
    System.out.println("Enter the id of the computer you want to display :");
  }

  /**
   * Display computer.
   *
   * @param computer the computer
   */
  public void displayComputer(ComputerDTO computerDTO) {
    StringBuilder line = new StringBuilder();
    
    line.append(computerDTO.getName() + " (" + computerDTO.getId() + ") ");
    
    if(computerDTO.getIntroduced() != null) {
      line.append(", introduced " + computerDTO.getIntroduced());
    }
    
    if(computerDTO.getDiscontinued() != null) {        
      line.append(", discontinued " + computerDTO.getDiscontinued());    
    }
   
    if(computerDTO.getCompanyDTO() != null) {
      line.append(", by company " + computerDTO.getCompanyDTO().getName() + " (" + computerDTO.getCompanyDTO().getId() + ")");
    }
	  
	System.out.println(line);
  }
}
