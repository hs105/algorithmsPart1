<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang = "en">

<head>

<link rel="icon"          href="http://introcs.cs.princeton.edu/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="http://introcs.cs.princeton.edu/favicon.ico" type="image/x-icon">
<link rel="stylesheet"    href="http://introcs.cs.princeton.edu/java.css" type="text/css">

<title>StdRandom.java</title>

<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<meta NAME="AUTHOR" CONTENT="Robert Sedgewick and Kevin Wayne">
<meta NAME="DESCRIPTION" CONTENT="StdRandom code in Java">
<meta NAME="TITLE" CONTENT="StdRandom code in Java">
<meta NAME="KEYWORDS" CONTENT="StdRandom,java,programming,computer science,algorithm,program,code">
<meta NAME="ROBOTS" CONTENT="INDEX,FOLLOW">

</head>


<body>
<center><h1>StdRandom.java</h1></center><p><br>

Below is the syntax highlighted version of <a href = "StdRandom.java">StdRandom.java</a>
from <a href = "http://introcs.cs.princeton.edu/java/stdlib">&#167; Standard Libraries</a>.
&nbsp; Here is the <a href = "http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdRandom.html">Javadoc</a>.
<p><br>

<!-- Generator: GNU source-highlight 3.1.6
by Lorenzo Bettini
http://www.lorenzobettini.it
http://www.gnu.org/software/src-highlite -->
<pre><tt><span class="comment">/*************************************************************************</span>
<span class="comment"> *  Compilation:  javac StdRandom.java</span>
<span class="comment"> *  Execution:    java StdRandom</span>
<span class="comment"> *  Dependencies: StdOut.java</span>
<span class="comment"> *</span>
<span class="comment"> *  A library of static methods to generate pseudo-random numbers from</span>
<span class="comment"> *  different distributions (bernoulli, uniform, gaussian, discrete,</span>
<span class="comment"> *  and exponential). Also includes a method for shuffling an array.</span>
<span class="comment"> *</span>
<span class="comment"> *</span>
<span class="comment"> *  %  java StdRandom 5</span>
<span class="comment"> *  seed = 1316600602069</span>
<span class="comment"> *  59 16.81826  true 8.83954  0 </span>
<span class="comment"> *  32 91.32098  true 9.11026  0 </span>
<span class="comment"> *  35 10.11874  true 8.95396  3 </span>
<span class="comment"> *  92 32.88401  true 8.87089  0 </span>
<span class="comment"> *  72 92.55791  true 9.46241  0 </span>
<span class="comment"> *</span>
<span class="comment"> *  % java StdRandom 5</span>
<span class="comment"> *  seed = 1316600616575</span>
<span class="comment"> *  96 60.17070  true 8.72821  0 </span>
<span class="comment"> *  79 32.01607  true 8.58159  0 </span>
<span class="comment"> *  81 59.49065  true 9.10423  1 </span>
<span class="comment"> *  96 51.65818  true 9.02102  0 </span>
<span class="comment"> *  99 17.55771  true 8.99762  0 </span>
<span class="comment"> *</span>
<span class="comment"> *  % java StdRandom 5 1316600616575</span>
<span class="comment"> *  seed = 1316600616575</span>
<span class="comment"> *  96 60.17070  true 8.72821  0 </span>
<span class="comment"> *  79 32.01607  true 8.58159  0 </span>
<span class="comment"> *  81 59.49065  true 9.10423  1 </span>
<span class="comment"> *  96 51.65818  true 9.02102  0 </span>
<span class="comment"> *  99 17.55771  true 8.99762  0 </span>
<span class="comment"> *</span>
<span class="comment"> *</span>
<span class="comment"> *  Remark</span>
<span class="comment"> *  ------</span>
<span class="comment"> *    - Relies on randomness of nextDouble() method in java.util.Random</span>
<span class="comment"> *      to generate pseudorandom numbers in [0, 1).</span>
<span class="comment"> *</span>
<span class="comment"> *    - This library allows you to set and get the pseudorandom number seed.</span>
<span class="comment"> *</span>
<span class="comment"> *    - See </span><span class="url">http://www.honeylocust.com/RngPack/</span><span class="comment"> for an industrial</span>
<span class="comment"> *      strength random number generator in Java.</span>
<span class="comment"> *</span>
<span class="comment"> *************************************************************************/</span>

<span class="preproc">import</span><span class="normal"> java</span><span class="symbol">.</span><span class="normal">util</span><span class="symbol">.</span><span class="normal">Random</span><span class="symbol">;</span>

<span class="comment">/**</span>
<span class="comment"> *  </span><span class="keyword">&lt;i&gt;</span><span class="comment">Standard random</span><span class="keyword">&lt;/i&gt;</span><span class="comment">. This class provides methods for generating</span>
<span class="comment"> *  random number from various distributions.</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  For additional documentation, see </span><span class="keyword">&lt;a</span><span class="normal"> </span><span class="type">href</span><span class="symbol">=</span><span class="string">"http://introcs.cs.princeton.edu/22library"</span><span class="keyword">&gt;</span><span class="comment">Section 2.2</span><span class="keyword">&lt;/a&gt;</span><span class="comment"> of</span>
<span class="comment"> *  </span><span class="keyword">&lt;i&gt;</span><span class="comment">Introduction to Programming in Java: An Interdisciplinary Approach</span><span class="keyword">&lt;/i&gt;</span><span class="comment"> by Robert Sedgewick and Kevin Wayne.</span>
<span class="comment"> *</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Robert Sedgewick</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Kevin Wayne</span>
<span class="comment"> */</span>
<span class="keyword">public</span><span class="normal"> </span><span class="keyword">final</span><span class="normal"> </span><span class="keyword">class</span><span class="normal"> </span><span class="classname">StdRandom</span><span class="normal"> </span><span class="cbracket">{</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="usertype">Random</span><span class="normal"> random</span><span class="symbol">;</span><span class="normal">    </span><span class="comment">// pseudo-random number generator</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">long</span><span class="normal"> seed</span><span class="symbol">;</span><span class="normal">        </span><span class="comment">// pseudo-random number generator seed</span>

<span class="normal">    </span><span class="comment">// static initializer</span>
<span class="normal">    </span><span class="keyword">static</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="comment">// this is how the seed was set in Java 1.4</span>
<span class="normal">        seed </span><span class="symbol">=</span><span class="normal"> System</span><span class="symbol">.</span><span class="function">currentTimeMillis</span><span class="symbol">();</span>
<span class="normal">        random </span><span class="symbol">=</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">Random</span><span class="symbol">(</span><span class="normal">seed</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// don't instantiate</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="function">StdRandom</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span><span class="normal"> </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Sets the seed of the psedurandom number generator.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">setSeed</span><span class="symbol">(</span><span class="type">long</span><span class="normal"> s</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        seed   </span><span class="symbol">=</span><span class="normal"> s</span><span class="symbol">;</span>
<span class="normal">        random </span><span class="symbol">=</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">Random</span><span class="symbol">(</span><span class="normal">seed</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns the seed of the psedurandom number generator.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">long</span><span class="normal"> </span><span class="function">getSeed</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> seed</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Return real number uniformly in [0, 1).</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> random</span><span class="symbol">.</span><span class="function">nextDouble</span><span class="symbol">();</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns an integer uniformly between 0 (inclusive) and N (exclusive).</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException if </span><span class="keyword">&lt;tt&gt;</span><span class="comment">N &lt;= 0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> N</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">N </span><span class="symbol">&lt;=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Parameter N must be positive"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> random</span><span class="symbol">.</span><span class="function">nextInt</span><span class="symbol">(</span><span class="normal">N</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">///////////////////////////////////////////////////////////////////////////</span>
<span class="normal">    </span><span class="comment">//  STATIC METHODS BELOW RELY ON JAVA.UTIL.RANDOM ONLY INDIRECTLY VIA</span>
<span class="normal">    </span><span class="comment">//  THE STATIC METHODS ABOVE.</span>
<span class="normal">    </span><span class="comment">///////////////////////////////////////////////////////////////////////////</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number uniformly in [0, 1).</span>
<span class="comment">     * </span><span class="type">@deprecated</span><span class="comment"> clearer to use {</span><span class="type">@link</span><span class="comment"> #uniform()}</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">random</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">();</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns an integer uniformly in [a, b).</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException if </span><span class="keyword">&lt;tt&gt;</span><span class="comment">b &lt;= a</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException if </span><span class="keyword">&lt;tt&gt;</span><span class="comment">b - a &gt;= Integer.MAX_VALUE</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="type">int</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> b</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">b </span><span class="symbol">&lt;=</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Invalid range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">((</span><span class="type">long</span><span class="symbol">)</span><span class="normal"> b </span><span class="symbol">-</span><span class="normal"> a </span><span class="symbol">&gt;=</span><span class="normal"> Integer</span><span class="symbol">.</span><span class="normal">MAX_VALUE</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Invalid range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> a </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">b </span><span class="symbol">-</span><span class="normal"> a</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number uniformly in [a, b).</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">a &lt; b</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">double</span><span class="normal"> b</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">a </span><span class="symbol">&lt;</span><span class="normal"> b</span><span class="symbol">))</span><span class="normal"> </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Invalid range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> a </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">()</span><span class="normal"> </span><span class="symbol">*</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">b</span><span class="symbol">-</span><span class="normal">a</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a boolean, which is true with probability p, and false otherwise.</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">p &gt;= 0.0</span><span class="keyword">&lt;/tt&gt;</span><span class="comment"> and </span><span class="keyword">&lt;tt&gt;</span><span class="comment">p &lt;= 1.0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">bernoulli</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> p</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">p </span><span class="symbol">&gt;=</span><span class="normal"> </span><span class="number">0.0</span><span class="normal"> </span><span class="symbol">&amp;&amp;</span><span class="normal"> p </span><span class="symbol">&lt;=</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Probability must be between 0.0 and 1.0"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">()</span><span class="normal"> </span><span class="symbol">&lt;</span><span class="normal"> p</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a boolean, which is true with probability .5, and false otherwise.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">bernoulli</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">bernoulli</span><span class="symbol">(</span><span class="number">0.5</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number with a standard Gaussian distribution.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">gaussian</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="comment">// use the polar form of the Box-Muller transform</span>
<span class="normal">        </span><span class="type">double</span><span class="normal"> r</span><span class="symbol">,</span><span class="normal"> x</span><span class="symbol">,</span><span class="normal"> y</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">do</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            x </span><span class="symbol">=</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(-</span><span class="number">1.0</span><span class="symbol">,</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">);</span>
<span class="normal">            y </span><span class="symbol">=</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(-</span><span class="number">1.0</span><span class="symbol">,</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">);</span>
<span class="normal">            r </span><span class="symbol">=</span><span class="normal"> x</span><span class="symbol">*</span><span class="normal">x </span><span class="symbol">+</span><span class="normal"> y</span><span class="symbol">*</span><span class="normal">y</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span><span class="normal"> </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">r </span><span class="symbol">&gt;=</span><span class="normal"> </span><span class="number">1</span><span class="normal"> </span><span class="symbol">||</span><span class="normal"> r </span><span class="symbol">==</span><span class="normal"> </span><span class="number">0</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> x </span><span class="symbol">*</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">sqrt</span><span class="symbol">(-</span><span class="number">2</span><span class="normal"> </span><span class="symbol">*</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">log</span><span class="symbol">(</span><span class="normal">r</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">/</span><span class="normal"> r</span><span class="symbol">);</span>

<span class="normal">        </span><span class="comment">// Remark:  y * Math.sqrt(-2 * Math.log(r) / r)</span>
<span class="normal">        </span><span class="comment">// is an independent random gaussian</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number from a gaussian distribution with given mean and stddev</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">gaussian</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> mean</span><span class="symbol">,</span><span class="normal"> </span><span class="type">double</span><span class="normal"> stddev</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> mean </span><span class="symbol">+</span><span class="normal"> stddev </span><span class="symbol">*</span><span class="normal"> </span><span class="function">gaussian</span><span class="symbol">();</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns an integer with a geometric distribution with mean 1/p.</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">p &gt;= 0.0</span><span class="keyword">&lt;/tt&gt;</span><span class="comment"> and </span><span class="keyword">&lt;tt&gt;</span><span class="comment">p &lt;= 1.0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">geometric</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> p</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">p </span><span class="symbol">&gt;=</span><span class="normal"> </span><span class="number">0.0</span><span class="normal"> </span><span class="symbol">&amp;&amp;</span><span class="normal"> p </span><span class="symbol">&lt;=</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Probability must be between 0.0 and 1.0"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="comment">// using algorithm given by Knuth</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="symbol">)</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">ceil</span><span class="symbol">(</span><span class="normal">Math</span><span class="symbol">.</span><span class="function">log</span><span class="symbol">(</span><span class="function">uniform</span><span class="symbol">())</span><span class="normal"> </span><span class="symbol">/</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">log</span><span class="symbol">(</span><span class="number">1.0</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> p</span><span class="symbol">));</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Return an integer with a Poisson distribution with mean lambda.</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">lambda &gt; 0.0</span><span class="keyword">&lt;/tt&gt;</span><span class="comment"> and not infinite</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">poisson</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> lambda</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">lambda </span><span class="symbol">&gt;</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Parameter lambda must be positive"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">Double</span><span class="symbol">.</span><span class="function">isInfinite</span><span class="symbol">(</span><span class="normal">lambda</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Parameter lambda must not be infinite"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="comment">// using algorithm given by Knuth</span>
<span class="normal">        </span><span class="comment">// see http://en.wikipedia.org/wiki/Poisson_distribution</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> k </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span>
<span class="normal">        </span><span class="type">double</span><span class="normal"> p </span><span class="symbol">=</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">;</span>
<span class="normal">        </span><span class="type">double</span><span class="normal"> L </span><span class="symbol">=</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">exp</span><span class="symbol">(-</span><span class="normal">lambda</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">do</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            k</span><span class="symbol">++;</span>
<span class="normal">            p </span><span class="symbol">*=</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">();</span>
<span class="normal">        </span><span class="cbracket">}</span><span class="normal"> </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">p </span><span class="symbol">&gt;=</span><span class="normal"> L</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> k</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number with a Pareto distribution with parameter alpha.</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">alpha &gt; 0.0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">pareto</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> alpha</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">alpha </span><span class="symbol">&gt;</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Shape parameter alpha must be positive"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">pow</span><span class="symbol">(</span><span class="number">1</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(),</span><span class="normal"> </span><span class="symbol">-</span><span class="number">1.0</span><span class="symbol">/</span><span class="normal">alpha</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> </span><span class="number">1.0</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number with a Cauchy distribution.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">cauchy</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> Math</span><span class="symbol">.</span><span class="function">tan</span><span class="symbol">(</span><span class="normal">Math</span><span class="symbol">.</span><span class="normal">PI </span><span class="symbol">*</span><span class="normal"> </span><span class="symbol">(</span><span class="function">uniform</span><span class="symbol">()</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> </span><span class="number">0.5</span><span class="symbol">));</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a number from a discrete distribution: i with probability a[i].</span>
<span class="comment">     * throws IllegalArgumentException if sum of array entries is not (very nearly) equal to </span><span class="keyword">&lt;tt&gt;</span><span class="comment">1.0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     * throws IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">a[i] &gt;= 0.0</span><span class="keyword">&lt;/tt&gt;</span><span class="comment"> for each index </span><span class="keyword">&lt;tt&gt;</span><span class="comment">i</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">int</span><span class="normal"> </span><span class="function">discrete</span><span class="symbol">(</span><span class="type">double</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">double</span><span class="normal"> EPSILON </span><span class="symbol">=</span><span class="normal"> </span><span class="number">1E-14</span><span class="symbol">;</span>
<span class="normal">        </span><span class="type">double</span><span class="normal"> sum </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">&gt;=</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">))</span><span class="normal"> </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"array entry "</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="string">" must be nonnegative: "</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]);</span>
<span class="normal">            sum </span><span class="symbol">=</span><span class="normal"> sum </span><span class="symbol">+</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">sum </span><span class="symbol">&gt;</span><span class="normal"> </span><span class="number">1.0</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> EPSILON </span><span class="symbol">||</span><span class="normal"> sum </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">1.0</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> EPSILON</span><span class="symbol">)</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"sum of array entries does not approximately equal 1.0: "</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> sum</span><span class="symbol">);</span>

<span class="normal">        </span><span class="comment">// the for loop may not return a value when both r is (nearly) 1.0 and when the</span>
<span class="normal">        </span><span class="comment">// cumulative sum is less than 1.0 (as a result of floating-point roundoff error)</span>
<span class="normal">        </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(</span><span class="keyword">true</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">double</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">();</span>
<span class="normal">            sum </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">;</span>
<span class="normal">            </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">                sum </span><span class="symbol">=</span><span class="normal"> sum </span><span class="symbol">+</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">                </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">sum </span><span class="symbol">&gt;</span><span class="normal"> r</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">return</span><span class="normal"> i</span><span class="symbol">;</span>
<span class="normal">            </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Returns a real number from an exponential distribution with rate lambda.</span>
<span class="comment">     * </span><span class="type">@throws</span><span class="comment"> IllegalArgumentException unless </span><span class="keyword">&lt;tt&gt;</span><span class="comment">lambda &gt; 0.0</span><span class="keyword">&lt;/tt&gt;</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">double</span><span class="normal"> </span><span class="function">exp</span><span class="symbol">(</span><span class="type">double</span><span class="normal"> lambda</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!(</span><span class="normal">lambda </span><span class="symbol">&gt;</span><span class="normal"> </span><span class="number">0.0</span><span class="symbol">))</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IllegalArgumentException</span><span class="symbol">(</span><span class="string">"Rate lambda must be positive"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="symbol">-</span><span class="normal">Math</span><span class="symbol">.</span><span class="function">log</span><span class="symbol">(</span><span class="number">1</span><span class="normal"> </span><span class="symbol">-</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">())</span><span class="normal"> </span><span class="symbol">/</span><span class="normal"> lambda</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of an array in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> N </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> N</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">N</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and N-1</span>
<span class="normal">            </span><span class="usertype">Object</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of a double array in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="type">double</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> N </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> N</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">N</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and N-1</span>
<span class="normal">            </span><span class="type">double</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of an int array in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="type">int</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> N </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> N</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">N</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and N-1</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of the subarray a[lo..hi] in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">lo </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="normal"> </span><span class="symbol">||</span><span class="normal"> lo </span><span class="symbol">&gt;</span><span class="normal"> hi </span><span class="symbol">||</span><span class="normal"> hi </span><span class="symbol">&gt;=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IndexOutOfBoundsException</span><span class="symbol">(</span><span class="string">"Illegal subarray range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">hi</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and hi</span>
<span class="normal">            </span><span class="usertype">Object</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of the subarray a[lo..hi] in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="type">double</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">lo </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="normal"> </span><span class="symbol">||</span><span class="normal"> lo </span><span class="symbol">&gt;</span><span class="normal"> hi </span><span class="symbol">||</span><span class="normal"> hi </span><span class="symbol">&gt;=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IndexOutOfBoundsException</span><span class="symbol">(</span><span class="string">"Illegal subarray range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">hi</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and hi</span>
<span class="normal">            </span><span class="type">double</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearrange the elements of the subarray a[lo..hi] in random order.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">shuffle</span><span class="symbol">(</span><span class="type">int</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">lo </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="normal"> </span><span class="symbol">||</span><span class="normal"> lo </span><span class="symbol">&gt;</span><span class="normal"> hi </span><span class="symbol">||</span><span class="normal"> hi </span><span class="symbol">&gt;=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">throw</span><span class="normal"> </span><span class="keyword">new</span><span class="normal"> </span><span class="function">IndexOutOfBoundsException</span><span class="symbol">(</span><span class="string">"Illegal subarray range"</span><span class="symbol">);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> r </span><span class="symbol">=</span><span class="normal"> i </span><span class="symbol">+</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="normal">hi</span><span class="symbol">-</span><span class="normal">i</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">);</span><span class="normal">     </span><span class="comment">// between i and hi</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> temp </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">];</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">r</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> temp</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Unit test.</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">main</span><span class="symbol">(</span><span class="normal">String</span><span class="symbol">[]</span><span class="normal"> args</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> N </span><span class="symbol">=</span><span class="normal"> Integer</span><span class="symbol">.</span><span class="function">parseInt</span><span class="symbol">(</span><span class="normal">args</span><span class="symbol">[</span><span class="number">0</span><span class="symbol">]);</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">args</span><span class="symbol">.</span><span class="normal">length </span><span class="symbol">==</span><span class="normal"> </span><span class="number">2</span><span class="symbol">)</span><span class="normal"> StdRandom</span><span class="symbol">.</span><span class="function">setSeed</span><span class="symbol">(</span><span class="normal">Long</span><span class="symbol">.</span><span class="function">parseLong</span><span class="symbol">(</span><span class="normal">args</span><span class="symbol">[</span><span class="number">1</span><span class="symbol">]));</span>
<span class="normal">        </span><span class="type">double</span><span class="symbol">[]</span><span class="normal"> t </span><span class="symbol">=</span><span class="normal"> </span><span class="cbracket">{</span><span class="normal"> </span><span class="symbol">.</span><span class="number">5</span><span class="symbol">,</span><span class="normal"> </span><span class="symbol">.</span><span class="number">3</span><span class="symbol">,</span><span class="normal"> </span><span class="symbol">.</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> </span><span class="symbol">.</span><span class="number">1</span><span class="normal"> </span><span class="cbracket">}</span><span class="symbol">;</span>

<span class="normal">        StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">(</span><span class="string">"seed = "</span><span class="normal"> </span><span class="symbol">+</span><span class="normal"> StdRandom</span><span class="symbol">.</span><span class="function">getSeed</span><span class="symbol">());</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> N</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">printf</span><span class="symbol">(</span><span class="string">"%2d "</span><span class="normal">  </span><span class="symbol">,</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="number">100</span><span class="symbol">));</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">printf</span><span class="symbol">(</span><span class="string">"%8.5f "</span><span class="symbol">,</span><span class="normal"> </span><span class="function">uniform</span><span class="symbol">(</span><span class="number">10.0</span><span class="symbol">,</span><span class="normal"> </span><span class="number">99.0</span><span class="symbol">));</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">printf</span><span class="symbol">(</span><span class="string">"%5b "</span><span class="normal">  </span><span class="symbol">,</span><span class="normal"> </span><span class="function">bernoulli</span><span class="symbol">(.</span><span class="number">5</span><span class="symbol">));</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">printf</span><span class="symbol">(</span><span class="string">"%7.5f "</span><span class="symbol">,</span><span class="normal"> </span><span class="function">gaussian</span><span class="symbol">(</span><span class="number">9.0</span><span class="symbol">,</span><span class="normal"> </span><span class="symbol">.</span><span class="number">2</span><span class="symbol">));</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">printf</span><span class="symbol">(</span><span class="string">"%2d "</span><span class="normal">  </span><span class="symbol">,</span><span class="normal"> </span><span class="function">discrete</span><span class="symbol">(</span><span class="normal">t</span><span class="symbol">));</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">();</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        String</span><span class="symbol">[]</span><span class="normal"> a </span><span class="symbol">=</span><span class="normal"> </span><span class="string">"A B C D E F G"</span><span class="symbol">.</span><span class="function">split</span><span class="symbol">(</span><span class="string">" "</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="usertype">String</span><span class="normal"> s </span><span class="symbol">:</span><span class="normal"> a</span><span class="symbol">)</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">print</span><span class="symbol">(</span><span class="normal">s </span><span class="symbol">+</span><span class="normal"> </span><span class="string">" "</span><span class="symbol">);</span>
<span class="normal">        StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">();</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="cbracket">}</span>
</tt></pre>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-10811519-1");
pageTracker._trackPageview();
} catch(err) {}</script>

</body>

<p><br><address><small>
Copyright &copy; 2000&ndash;2011, Robert Sedgewick and Kevin Wayne.
<br>Last updated: Tue Mar 25 20:35:06 EDT 2014.
</small></address>

</html>
