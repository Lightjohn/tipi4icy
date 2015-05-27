package plugins.mitiv.blocks;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import icy.gui.frame.progress.AnnounceFrame;
import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.type.collection.array.Array1DUtil;
import plugins.adufour.blocks.lang.Block;
import plugins.adufour.blocks.util.VarList;
import plugins.adufour.ezplug.EzPlug;
import plugins.adufour.ezplug.EzVarBoolean;
import plugins.adufour.ezplug.EzVarSequence;

public class MitivBlockFFT extends EzPlug implements Block {

    private EzVarSequence input = new EzVarSequence("Input sequence");
    private EzVarSequence output = new EzVarSequence("Output sequence");
    private EzVarBoolean inverse = new EzVarBoolean("inverse", false);

    private DoubleFFT_1D fft1D;
    private int[] dims;
    public MitivBlockFFT() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void declareInput(VarList inputMap) {
        inputMap.add("input", input.getVariable());
        inputMap.add("inverse", inverse.getVariable());
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
            if (in.getSizeT() != 1) {
                throw new IllegalArgumentException("Only 2D or 3D data input");
            }
            int rank = 2;
            dims = new int[]{in.getSizeX(),in.getSizeY()};
            if (in.getSizeZ() != 1) {
                rank = 3;
                dims = new int[]{in.getSizeX(),in.getSizeY(), in.getSizeZ()};
            }
            double[] inputData = Array1DUtil.arrayToDoubleArray(in.getDataCopyXYZT( 0 ), in.isSignedDataType());
            double[] outputData;
            if (inverse.getValue()) {   //Inverse
                IFFT1D(inputData);
                outputData = new double[inputData.length/2];
                for (int i = 0; i < inputData.length/2; i++) {
                    outputData[i] = inputData[2*i];
                }
                System.out.println("Inverse");
            } else {    //DIRECT
                outputData = new double[inputData.length*2];
                for (int i = 0; i < inputData.length; i++) {
                    outputData[i] = inputData[i];
                }
                FFT1D(outputData);
                System.out.println("direct");
            }
            Sequence out;
            if (rank == 2) {
                if (inverse.getValue()) {
                    System.out.println(dims[0]+" "+ dims[1]/2+" "+outputData.length);
                    out = new Sequence(new IcyBufferedImage(dims[0], dims[1]/2, outputData));
                } else {
                    System.out.println(dims[0]+" "+ dims[1]*2);
                    out = new Sequence(new IcyBufferedImage(dims[0], dims[1]*2, outputData));
                }
            } else {
                
                throw new IllegalArgumentException("not implemented yet");
            }
            output.setValue(out);
            
        } catch(Exception e){
            new AnnounceFrame("Oops, Error: "+ e);
            e.printStackTrace();
        }
    }

    public void FFT1D(double[] array) {
        if(fft1D == null){
            fft1D = new DoubleFFT_1D(dims[0]*dims[1]);
        }
        fft1D.realForwardFull(array);
    }

    public void IFFT1D(double[] array) {
        if(fft1D == null){
            fft1D = new DoubleFFT_1D(dims[0]*dims[1]/2);
        }
        fft1D.complexInverse(array, true);
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
