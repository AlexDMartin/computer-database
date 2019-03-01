package com.excilys.view;

import java.util.List;

import com.excilys.dto.ComputerDTO;

/**
 * View used to list all computers
 */
public class ListComputerView {

  /** Singleton implementation of ListComputerView. */
  private static ListComputerView listComputerViewInstance = null;

  /**
   * Singleton implementation of ListComputerView.
   */
  private ListComputerView() {
  }

  /**
   * Singleton implementation of ListComputerView.
   *
   * @return single instance of ListComputerView
   */
  public static ListComputerView getInstance() {
    if (listComputerViewInstance == null) {
      listComputerViewInstance = new ListComputerView();
    }
    return listComputerViewInstance;
  }

  /**
   * Displays the view.
   *
   * @param A list of ComputerDTOs
   */
  public void render(List<ComputerDTO> computerDTOList) {
    for (ComputerDTO computerDTO : computerDTOList) {
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
     
      System.out.println(line.toString());
    }
  }
}
