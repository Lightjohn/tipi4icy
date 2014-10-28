/*
 * This file is part of TiPi (a Toolkit for Inverse Problems and Imaging)
 * developed by the MitiV project.
 *
 * Copyright (c) 2014 the MiTiV project, http://mitiv.univ-lyon1.fr/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package plugins.mitiv.deconv;

public class ToolTipText {

    static final String deconvolutionSlider = "<html>Update Mu value<br/>Recompute the image<br/>Third line</html>";
    static final String sequenceImage = "The image on which we will work";
    static final String sequencePSF = "The PSF associated to the image given";
    static final String sequenceWeigth = "The weight map to possibly ignore or minize errors in the image";
    static final String sequenceVariance = "The variance map";
    
    static final String doubleGrtoll = "Relative gradient tolerance for the convergence";
    static final String doubleMu = "Regularization level";
    static final String doubleEpsilon = "Threshold level";
    static final String doubleMaxIter = "Maximum number of iterations, -1 for no limits";
    static final String doublePadding = "Add zero around the image (2D and 3D)";
    
    static final String booleanPSFSplitted = "If the PSF is not centered check this box</html>";
    static final String booleanRestart = "Restart from previous result, if enabled will start with last image and PSF";
    
    static final String textMethod = "Choose the algorithm used to deconvoluate the image";
}


/*
 * Local Variables:
 * mode: Java
 * tab-width: 8
 * indent-tabs-mode: nil
 * c-basic-offset: 4
 * fill-column: 78
 * coding: utf-8
 * ispell-local-dictionary: "american"
 * End:
 */