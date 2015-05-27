package plugins.mitiv.blocks;

import mitiv.utils.MathUtils;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import icy.gui.frame.progress.AnnounceFrame;
import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.type.collection.array.Array1DUtil;
import plugins.adufour.blocks.lang.Block;
import plugins.adufour.blocks.util.VarList;
import plugins.adufour.ezplug.EzPlug;
import plugins.adufour.ezplug.EzVarSequence;

public class MitivBlockAbs2 extends EzPlug implements Block {

    private EzVarSequence input = new EzVarSequence("Input sequence");
    private EzVarSequence output = new EzVarSequence("Output sequence");

    private DoubleFFT_1D fft1D;
    private int[] dims;
    public MitivBlockAbs2() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void declareInput(VarList inputMap) {
        inputMap.add("input", input.getVariable());
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
            
            //CommonUtils.fftShift3D(inputData, outputData, dims[0], dims[1], 1);
            double[] outputData = MathUtils.abs2(inputData, 0);
            Sequence out;
            out = new Sequence(new IcyBufferedImage(dims[0], dims[1], outputData));
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
