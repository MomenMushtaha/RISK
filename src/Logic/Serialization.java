package Logic;
import View.GameView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class Serialization {
    public GameView view;


    public ObjectOutputStream save(JFileChooser fileChooser) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile() + " .sv"));
        return objectWriter;
    }


    public ObjectInputStream load(JFileChooser fileChooser) throws IOException, ClassNotFoundException {
        ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
        return objectReader;
    }


    public String mapper(File xmlFile) {
        String sAppendFileName = null;
        try {
            String importFileName;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose xml Map file");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                importFileName = fileChooser.getSelectedFile().getAbsolutePath();
                String substring = importFileName.trim().substring(importFileName.length() - 4);
                    if (substring.equals(".xml")){
                        File f = new File(importFileName);
                        //xmlFile.setName(f.getName());
                        //xmlFile.setPath(importFileName.substring(0, importFileName.lastIndexOf("\\")));
                        sAppendFileName=xmlFile.getPath();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "File name invalid");
                        String sPrint = substring;
                        System.out.println(sPrint);
                    }
                }
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sAppendFileName;
    }





    /**
     * Parses the and validate map.
     *
     * @param gameMap the game map
     * @param size the size
     * @return true, if successful
     */
   /* public boolean parseAndValidateMap(File gameMap, int size) {
        boolean isMapValid = false;
        try {
            FileReader mapFile;
            String line = null;
            mapFile = new FileReader(gameMap.getPath() + "\\" + gameMap.getName());
            String Data = "";
            BufferedReader mapReader = new BufferedReader(mapFile);
            while ((line = mapReader.readLine()) != null) {
                if (line != "\n") {
                    Data += line + "\n";
                }
            }
            if (Data.toLowerCase().indexOf("[continents]") >= 0 &&  Data.toLowerCase().indexOf("[territories]") >= 0 && Data.toLowerCase().indexOf("author") >= 0 && Data.toLowerCase().indexOf("[map]") >= 0 && Data.toLowerCase().indexOf("image") <= 0
                    && Data.toLowerCase().indexOf("wrap") <= 0 && Data.toLowerCase().indexOf("scroll") <= 0 && Data.toLowerCase().indexOf("warn") <= 0) {
                isMapValid = true;
                gameMap.setErrorOccurred(false);
                gameMap.setErrorMessage("No Errors");
            }
            else {
                isMapValid = false;
                gameMap.setErrorOccurred(true);
                gameMap.setErrorMessage("Information missing/Not in domination format");
                return isMapValid;
            }
            String authorData = Data.substring(Data.toLowerCase().indexOf("[map]"), Data.toLowerCase().indexOf("[continents]"));
            String continentData = Data.substring(Data.toLowerCase().indexOf("[continents]"), Data.toLowerCase().indexOf("[territories]"));
            String countryData = Data.substring(Data.toLowerCase().indexOf("[territories]"));
            String[] countryDataArray = countryData.split("\n");
            String[] continentDataArray = continentData.split("\n");
            for (String stringManipulation : continentDataArray) {
                if (!stringManipulation.equalsIgnoreCase("[continents]") && stringManipulation.length() >= 3) {
                    Continent newContinent = new Continent();
                    newContinent.setContinentName(stringManipulation.substring(0, stringManipulation.indexOf("=")).toUpperCase());
                    newContinent.setControlValue(Integer.parseInt(stringManipulation.substring(stringManipulation.indexOf("=") + 1)));
                    gameMap.getContinents().add(newContinent);
                }
            }
            for (String stringManipulation : countryDataArray) {
                if ((!stringManipulation.equalsIgnoreCase("[territories]") && stringManipulation.length() > 3)) {
                    if (stringManipulation.replaceAll("[^,]", "").length() >= 4) {
                        Country newCountry = new Country();
                        String[] stringManipulationArray = stringManipulation.split(",");
                        newCountry.setCountryName(stringManipulationArray[0]);
                        newCountry.setLatitude(Double.parseDouble(stringManipulationArray[1].trim()));
                        newCountry.setLongitude(Double.parseDouble(stringManipulationArray[2].trim()));
                        for (int i = 4; i < stringManipulationArray.length; i++) {
                            newCountry.getListOfNeighbours().add(stringManipulationArray[i]);
                        }
                        for (Continent currentContinent : gameMap.getContinents()) {
                            if (currentContinent.getContinentName().toLowerCase().indexOf(stringManipulationArray[3].trim().toLowerCase()) >= 0) {
                                currentContinent.getCountriesPresent().add(newCountry);
                            }
                        }
                    }
                    else {
                        gameMap.setErrorOccurred(true);
                        gameMap.setErrorMessage("Information missing");
                        break;
                    }
                }
            }
            isMapValid=validateMap(gameMap,size);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return isMapValid;
    }
*/

    /**
     * Validate map.
     *
     * @param gameMap the game map
     * @param size the size
     * @return true, if successful
     */
   /* public boolean validateMap(Map gameMap,int size) {
        if (!checkEmptyContinent(gameMap)) {
            if (!checkDuplicateContinents(gameMap)) {
                if (!checkDuplicateCountries(gameMap)) {
                    if (checkIfNeigbourExist(gameMap)) {
                        if(checkContientConnectivity(gameMap)) {
                            if (checkMapConnectivity(gameMap)) {
                                if (checkCountryCount(gameMap, size)) {
                                    return true;
                                }
                                else {
                                    return false;
                                }
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return false;

    }*/
}
