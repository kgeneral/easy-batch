/*
 *  The MIT License
 *
 *   Copyright (c) 2015, Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package org.easybatch.xml;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.OutputStreamWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easybatch.core.util.Utils.LINE_SEPARATOR;

/**
 * Test class for {@link XmlWrapperTagWriter}.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class XmlWrapperTagWriterTest {

    private static final String DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";

    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    private String wrapperTag;

    private XmlWrapperTagWriter xmlWrapperTagWriter;

    @Before
    public void setUp() throws Exception {
        wrapperTag = "tweets";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        xmlWrapperTagWriter = new XmlWrapperTagWriter(outputStreamWriter, wrapperTag);
    }

    @Test
    public void testBeforeJobStart() throws Exception {
        xmlWrapperTagWriter.beforeJobStart();
        assertThat(systemOut.getLog()).isEqualTo(DECLARATION + LINE_SEPARATOR + "<" + wrapperTag + ">" + LINE_SEPARATOR);
    }

    @Test
    public void testAfterJobEnd() throws Exception {
        xmlWrapperTagWriter.afterJobEnd();
        assertThat(systemOut.getLog()).isEqualTo("</" + wrapperTag + ">");
    }
}