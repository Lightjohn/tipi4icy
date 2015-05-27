package plugins.mitiv.blocks;

import icy.gui.frame.progress.AnnounceFrame;
import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.type.collection.array.Array1DUtil;
import plugins.adufour.blocks.lang.Block;
import plugins.adufour.blocks.util.VarList;
import plugins.adufour.ezplug.EzPlug;
import plugins.adufour.ezplug.EzVarSequence;
import plugins.adufour.ezplug.EzVarText;

public class MitivBlockRealImag extends EzPlug implements Block {

    private String real = "Real";
    private String im = "Imaginary";
    private EzVarSequence input = new EzVarSequence("Input sequence");
    private EzVarSequence output = new EzVarSequence("Output sequence");
    private EzVarText text = new EzVarText("part", new String[]{real,im}, false);

    private int[] dims;
    
    @Override
    public void declareInput(VarList inputMap) {
        inputMap.add("input", input.getVariable());
        inputMap.add("Choice", text.getVariable());
    }

    @Override
    public void declareOutput(VarList outputMap) {
        outputMap.add("Output", output.getVariable());

    }

    @Override
    public void run() {
        Sequence in = input.getValue();
        try {
            if (in == null) {
                throw new IllegalArgumentException("No input specified");
            }
            if (in.getSizeT() != 1 || in.getSizeZ() != 1) {
                throw new IllegalArgumentException("Only 2 data input");
            }
            dims = new int[]{in.getSizeX(),in.getSizeY()};

            double[] inputData = Array1DUtil.arrayToDoubleArray(in.getDataCopyXYZT( 0 ), in.isSignedDataType());
            
            double[] outputData = new double[inputData.length/2];
            if (text.getValue() == real) {
                for (int i = 0; i < outputData.length; i++) {
                    outputData[i] = inputData[2*i];
                }
            } else {
                for (int i = 0; i < outputData.length; i++) {
                    outputData[i] = inputData[2*i+1];
                }
            }

            Sequence out;
            out = new Sequence(new IcyBufferedImage(dims[0]/2, dims[1], outputData));
            output.setValue(out);

        } catch(Exception e){
            new AnnounceFrame("Oops, Error: "+ e);
            e.printStackTrace();
        }
    }
    
    @Override
    public void clean() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub

    }
}
