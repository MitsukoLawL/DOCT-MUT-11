<?xml version="1.0"?>
    <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

        <xsl:template match="/testsuite">
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <meta charset="utf-8" />
                <title>Groupe 11 - Mutation Testing</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <meta name="description" content="" />
                <meta name="author" content="" />

                <!-- Le styles -->

                <!-- Le styles -->
                <!-- Latest compiled and minified CSS BS 3.0. RC1-->
                <link href="css/theme.css" rel="stylesheet" />
                <link href="assets/css/jquery.mCustomScrollbar.css" rel="stylesheet" />

                <!-- GOOGLE FONT-->
                <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700italic,700,500&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css' />
                <!-- /GOOGLE FONT-->
                <link href="css/style.css" rel="stylesheet" />

                <link href="assets/css/font-awesome.min.css" rel="stylesheet" />
                <link rel="shortcut icon" href="assets/ico/favicon.ico" />
                <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png" />
                <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png" />
                <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png" />
                <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png" />
            </head>

            <body>

                <div class="container">
                    <!-- NAVBAR-->
                    <!-- Fixed navbar -->
                    <div class="navbar navbar-inverse">
                        <div class="container">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="#">Project name</a>
                            </div>
                            <div class="collapse navbar-collapse">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="#">Home</a></li>
                                    <li><a href="#about">About</a></li>
                                    <li><a href="#contact">Contact</a></li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Action</a></li>
                                            <li><a href="#">Another action</a></li>
                                            <li><a href="#">Something else here</a></li>
                                            <li class="divider"></li>
                                            <li class="dropdown-header">Nav header</li>
                                            <li><a href="#">Separated link</a></li>
                                            <li><a href="#">One more separated link</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!--/.nav-collapse -->
                        </div>
                    </div>
                </div>

                <div class="page-header">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4 col-lg-4">
                                <a class="brand" href="#">Mutation Groupe 11</a>
                            </div>
                            <div class="col-sm-8 col-lg-8">
                                <div class="well text-center">
                                    <!--<a href="http://www.bootstraptor.com">www.bootstraptor.com</a> ADDS HERE-->
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <!-- <div class="col-sm-12 col-lg-12">
                                <p class="alert alert-success" style="text-transform:uppercase; display:inline-block; box-sizing:border-box; -moz-box-sizing:border-box;width:100%;"><span class="label label-important">Announcement:</span> Here goes your news and infos lorem ipsum Here goes your news and infos lorem ipsum
                                </p>


                            </div> -->
                        </div>
                    </div>
                    <!-- PAGE-HEADER-->
                </div>


                <div class="container">
                    <!-- Example row of columns -->
                    <div class="row">
                        <!-- left SIDE-->
                        <div class="visible-md visible-lg col-sm-4 col-lg-4">
                            <div id="left-side-menu">
                                <div class="well">
                                    <div class="tabbable">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#1" data-toggle="tab">Popular</a></li>
                                            <li><a href="#2" data-toggle="tab">Latest</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="1">
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <div id="containerHigh" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

                                                    </div>
                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>PANE#1 Graphique Nombre de Test Fail/ Nombre de test total</p>

                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Graphique ....</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->

                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->


                                            </div>
                                            <div class="tab-pane" id="2">
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>PANE#2 Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>
                                                <!-- ITEM-->
                                                <div class="row">
                                                    <div class="col-sm-8 col-lg-8">
                                                        <p>Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                                    </div>
                                                    <div class="col-sm-4 col-lg-4">
                                                        <div class="thumbnail">
                                                            <img class="img-responsive" src="images/thumb.png" alt="post image" />
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- / ITEM-->
                                                <hr/>

                                            </div>

                                        </div>
                                    </div>
                                    <!-- /TABS-->
                                </div>
                                <!-- /well-->
                            </div>
                        </div>
                        <!-- /left SIDE-->


                        <!-- CONTENT SIDE-->
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                            <!-- INNER ROW-->
                            <div class="row">
                                <div class="col-sm-12 col-lg-12">
                                    <!-- article-->
                                    <h1>Your site slogan goes here</h1>

                                    <!--/ article-->
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                                    <!--<div class="thumbnail">
                        <img class="img-responsive" src="images/thumb.png" alt="post image"/>
                    </div>-->
                                    <!--<p style="margin-top:10px;"><strong>BY: ADMIN</strong> | 05.01.2013</p>-->
                                    <h2>Mutation Result</h2>
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr class="info">
                                                <th>TestClass</th>
                                                <th>Class</th>
                                                <th>Error</th>
                                                <th>Time</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <xsl:for-each select="testcase">
                                                <tr>
                                                    <td>
                                                        <xsl:value-of select="@name" />
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="@classname" />
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="failure/@type" />
                                                    </td>
                                                    <td>
                                                        <xsl:value-of select="@time" />
                                                    </td>

                                                </tr>
                                            </xsl:for-each>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">

                                </div>
                            </div>


                            <!-- /INNER ROW-FLUID-->
                            <hr/>
                            <!-- related projects -->
                            <div class="articles-grid row">

                            </div>
                            <!-- related projects -->
                            <ul class="pager">
                                <li class="pull-right"><a href="#">Next page ></a></li>
                            </ul>

                            <!-- FOOTER-->
                            <footer>
                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                                        <h4 class="line3 center standart-h4title"><span>Navigation</span></h4>
                                        <ul class="footer-links">
                                            <li><a href="#">Home</a></li>
                                            <li><a href="#">project</a></li>
                                            <li><a href="#">Elements</a></li>
                                            <li><a href="#">Contact</a></li>
                                            <li><a href="#">Blog</a></li>
                                        </ul>
                                    </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                                        <h4 class="line3 center standart-h4title"><span>Useful Links</span></h4>
                                        <ul class="footer-links">
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstrap templates</a></li>
                                        </ul>
                                    </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                                        <h4 class="line3 center standart-h4title"><span>Useful Links</span></h4>
                                        <ul class="footer-links">
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstraptor.com</a></li>
                                            <li><a href="http://www.bootstraptor.com">Bootstrap templates</a></li>
                                        </ul>
                                    </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3">
                                        <h4 class="line3 center standart-h4title"><span>Our office</span></h4>
                                        <address>
			<strong>bootstraptor.com, LLC.</strong><br/>
			<i class="fa-icon-map-marker"></i> 795 Folsom Ave, Suite 600<br/>
			San Francisco, CA 94107<br/>
			<i class="fa-icon-phone-sign"></i> + 4 (123) 456-7890
		
		</address>
                                    </div>

                                </div>
                                <hr class="clearfix" />
                                <div class="row">
                                    <div class="col-sm-12 col-lg-12">

                                        <p>2016 © DevOps - AD </p>
                                    </div>
                                </div>
                            </footer>

                        </div>
                        <!-- /CONTENT SIDE-->




                    </div>




                </div>
                <!-- /container -->
                <!--/ CONTENT -->






                <!-- Le javascript
    ================================================== -->
                <!-- Placed at the end of the document so the pages load faster -->
                <script src="assets/js/jquery.js" type="text/javascript"></script>

                <!-- Latest compiled and minified JavaScript -->
                <!-- NOTE!!! This template use custom bootstrap_mode_blogalt.js file by @Bootstraptor for mCustomScrollbar plugin normal working with tabs 
Bootstrap plugin line 1705 bootstrap_mode_blogalt.js
do not remove or change this note!
########################################################################################################################
-->
                <script src="assets/js/bootstrap_mode_blogalt.js"></script>

                <script src="assets/js/jquery.localscroll-1.2.7-min.js" type="text/javascript"></script>
                <script src="assets/js/jquery.scrollTo-1.4.2-min.js" type="text/javascript"></script>
                <script src="assets/js/jquery.mousewheel.js" type="text/javascript"></script>
                <script src="assets/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>

                <!-- CUSTOM AFFIX PLUGIN by @Bootstraptor -->
                <script>
                    var nav = jQuery('#left-side-menu');
                    jQuery(window).scroll(function () {
                        if (jQuery(this).scrollTop() > 150) {
                            nav.addClass("fixed-top");
                        } else {
                            nav.removeClass("fixed-top");
                        }
                    });
                </script>

                <script src="js/highchartsfunction.js"></script>
                <script src="https://code.highcharts.com/highcharts.js"></script>
                <script src="https://code.highcharts.com/modules/exporting.js"></script>


                <!--This function integrated in bootstrap_mode_blogalt.js line 1835
<script>
    (function($){
        $(window).load(function(){
            $(".tab-pane").mCustomScrollbar();  


        });
    })(jQuery);
</script>
-->

            </body>

            </html>

        </xsl:template>
    </xsl:stylesheet>