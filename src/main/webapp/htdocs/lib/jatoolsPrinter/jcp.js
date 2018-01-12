var JCP = {
    setup: {
        download: "http://print.jatools.com/jcp/setup.exe",
        noSetupMessage: "杰表云打印（JCP）未安装, 请下载安装之."
    }
}
var X = "www.jatools.com";

function Jcp(au, forward, password) {
    var V = JSON.parse || function (str) {
        if (str === "")
            str = '""';
        eval("var p=" + str + ";");
        return p;
    };

    function log(e) {
    };

    function ajax(url, data, aa, result) {
        try {
            if (data && typeof(data) === 'object') {
                data.tab = ai || "";
                data = JSONstringify(data);
            }
            var x = new (window.XDomainRequest || window.XMLHttpRequest || ActiveXObject)('MSXML2.XMLHTTP.3.0');
            x.open(data ? 'POST' : 'GET', url, 1);
            if (window.XDomainRequest) {
                x.onload = function () {
                    var data = V(x.responseText || "{}");
                    if (result) {
                        data = typeof(data.result) == "undefined" ? "" : data.result;
                    }
                    aa && aa(data, x.responseText, 200);
                };
                x.aA = function () {
                    aa && aa(data, x.responseText, 404);
                };
                x.onerror = function () {
                    aa && aa(data, x.responseText, 404);
                };
                x.send(data)
            } else {
                x.onreadystatechange = function () {
                    try {
                        var data = V(x.responseText || "{}");
                        if (result) {
                            data = typeof(data.result) == "undefined" ? "" : data.result;
                        }
                        x.readyState > 3 && aa && aa(data, x.responseText, x.status);
                    } catch (e) {
                        log(e);
                    }
                };
                x.send(data)
            }
        } catch (e) {
            log(e);
        }
    };

    function l(ad, id) {
        return ad.getElementById(id);
    };

    function m(d) {
        var b = "";
        var e = d.styleSheets;
        for (var g = 0; g < e.length; g++) {
            var h = e[g];
            try {
                var c = h.cssRules;
                if (c) {
                    for (var a = 0; a < c.length; a++) {
                        b += c[a].cssText || ""
                    }
                } else {
                    if (h.cssText) {
                        b += h.cssText
                    }
                }
            } catch (f) {
            }
        }
        return (b + "").replace(/[\s]*\n/gm, "");
    };

    function D(ad, node) {
        if (ad.doctype)
            node.setAttribute('_strict', 'true');
        return node.outerHTML || (function (n) {
            var div = ad.createElement('div'), h;
            div.appendChild(n.cloneNode(true));
            h = div.innerHTML;
            div = null;
            return h;
        })(node);
    };
    var aK;

    function F(ad, myDoc) {
        if (typeof(ad.getElementById) != 'undefined') {
            var html = '';
            var result = {
                style: m(ad)
            };
            if (myDoc.pages) {
                for (var i = 0; i < myDoc.pages.length; i++) {
                    var page = myDoc.pages[i];
                    if (typeof(page.substring) != 'undefined') {
                        page = l(ad, page);
                    }
                    html += ("<div id='page" + (i + 1) + "'>" + D(ad, page) + "</div>");
                }
            } else {
                if (myDoc.jobPages) {
                    if (!myDoc.jobBase) {
                        myDoc.jobBase = 0;
                        aK = myDoc.done || null;
                        var i = 0;
                        while (true) {
                            var page = l(ad, (myDoc.pagePrefix || '') + 'page' + (i + 1));
                            if (!page)
                                break;
                            i++;
                        }
                        myDoc.aM = i;
                    }
                    for (var i = 0; i < myDoc.jobPages; i++) {
                        var page = l(ad, (myDoc.pagePrefix || '') + 'page' + (i + myDoc.jobBase + 1));
                        if (!page)
                            break;
                        html += D(ad, page);
                    }
                    if (myDoc.done) {
                        myDoc.done = function () {
                            var page = l(ad, (myDoc.pagePrefix || '') + 'page' + (i + myDoc.jobBase + 1));
                            if (!page && aK) {
                                aK();
                            } else {
                                myDoc.jobBase = myDoc.jobBase + myDoc.jobPages;
                                myDoc.documents = document;
                                self.print(myDoc, false);
                            }
                        }
                    }
                } else {
                    var i = 0;
                    while (true) {
                        var page = l(ad, (myDoc.pagePrefix || '') + 'page' + (i + 1));
                        if (!page)
                            break;
                        html += D(ad, page);
                        i++;
                    }
                }
            }
            result.pages = html;
            return result;
        } else
            return ad;
    };

    function C(myDoc) {
        myDoc.documents = w(myDoc);
        if (myDoc.footer && myDoc.footer.html.innerHTML) {
            G(myDoc.footer.html);
            myDoc.footer.html = myDoc.footer.html.innerHTML;
        }
        if (myDoc.header && myDoc.header.html.innerHTML) {
            G(myDoc.header.html);
            myDoc.header.html = myDoc.header.html.innerHTML;
        }
        return myDoc;
    };

    function B(myDoc, aa) {
        var ae = myDoc.documents;
        var av = false;
        if (ae.substring) {
            av = true;
            ae = [ae];
        }
        var needs = 0;
        var wrapper = document.getElementById("_jp_wrapper");
        if (!wrapper) {
            wrapper = document.createElement("div");
            wrapper.style.position = 'absolute';
            wrapper.style.left = '-3000px';
            wrapper.style.width = '2500px';
            wrapper.id = '_jp_wrapper';
            document.body.appendChild(wrapper);
        } else
            wrapper.innerHTML = '';
        var loaded = function () {
            if (true || (event.srcElement.readyState || '') == "complete") {
                ae[event.srcElement.ownerIndex] = event.srcElement.contentWindow.document;
                needs--;
                if (needs == 0) {
                    if (av) {
                        myDoc.documents = ae[0];
                    }
                    aa();
                }
            }
        };
        if (typeof(ae.push) != 'undefined') {
            for (var i = 0; i < ae.length; i++) {
                if (ae[i].substring) {
                    needs++;
                    var frame = document.createElement("iframe");
                    frame.ownerIndex = i;
                    if (frame.attachEvent) {
                        frame.attachEvent("onload", loaded);
                    } else {
                        frame.onload = loaded;
                    }
                    frame.src = ae[i];
                    wrapper.appendChild(frame);
                }
            }
        } else {
            if (av) {
                myDoc.documents = ae[0];
            }
            aa();
        }
    };

    function G(target) {
        var properties = ['border', 'border-radius', 'box-shadow', 'height', 'margin', 'padding', 'width2', 'max-width', 'min-width', 'border-collapse', 'border-spacing',
            'caption-side', 'empty-cells', 'table-layout', 'direction', 'font', 'font-family', 'font-style', 'font-variant', 'font-size', 'font-weight', 'letter-spacing',
            'line-height', 'text-align', 'text-decoration', 'text-indent', 'text-overflow', 'text-shadow', 'text-transform', 'white-space', 'word-spacing', 'word-wrap',
            'vertical-align', 'color', 'background', 'background-color', 'background-image', 'background-position', 'background-repeat', 'Opacity', 'bottom', 'clear', 'clip',
            'cursor', 'display', 'float', 'left', 'opacity', 'outline ', 'overflow', 'position', 'resize ', 'right', 'top', 'visibility', 'z-index', 'list-style-image',
            'list-style-position', 'list-style-type'];
        var elements = target.getElementsByTagName('*');
        for (var e = 0; e < elements.length; e++) {
            var el = elements.item(e);
            if (el.tagName == 'IMG') {
                el.src = el.src;
            }
            var thisProps = (el.getAttribute("style") || '').split(";");
            for (var p = 0; p < properties.length; p++) {
                var thisProp = properties[p];
                var thisValue = null;
                if (el.currentStyle) {
                    thisValue = el.currentStyle[thisProp];
                } else if (window.getComputedStyle) {
                    if (window.getComputedStyle.getPropertyValue) {
                        thisValue = window.getComputedStyle(el, null).getPropertyValue(thisProp)
                    } else {
                        thisValue = window.getComputedStyle(el)[thisProp]
                    }
                    ;
                }
                if (thisValue) {
                    el.style[thisProp] = thisValue;
                }
            }
        }
    };

    function w(myDoc) {
        var ae = myDoc.documents, result = null;
        if (typeof(ae.push) != 'undefined') {
            result = [];
            for (var i = 0; i < ae.length; i++) {
                result.push(F(ae[i], myDoc));
            }
            return result;
        } else {
            return F(ae, myDoc);
        }
    };

    function t(target) {
        var result = "<html><head><style>" + m(target.ownerDocument) + "</style></head><body>" + D(target.ownerDocument, target) + '</body></html>';
        return result;
    };

    function A(ad) {
        var result = "<html><head><base href='" + ad.URL + "'/><style>" + m(ad) + "</style></head><body>" + ad.body.innerHTML + '</body></html>';
        return result;
    };
    var lut = [];
    for (var i = 0; i < 256; i++) {
        lut[i] = (i < 16 ? '0' : '') + (i).toString(16);
    }

    function K() {
        var d0 = Math.random() * 0xffffffff | 0;
        var d1 = Math.random() * 0xffffffff | 0;
        var d2 = Math.random() * 0xffffffff | 0;
        var d3 = Math.random() * 0xffffffff | 0;
        return lut[d0 & 0xff] + lut[d0 >> 8 & 0xff] + lut[d0 >> 16 & 0xff] + lut[d0 >> 24 & 0xff] + '-' + lut[d1 & 0xff] + lut[d1 >> 8 & 0xff] + '-' + lut[d1 >> 16 & 0x0f | 0x40]
            + lut[d1 >> 24 & 0xff] + '-' + lut[d2 & 0x3f | 0x80] + lut[d2 >> 8 & 0xff] + '-' + lut[d2 >> 16 & 0xff] + lut[d2 >> 24 & 0xff] + lut[d3 & 0xff]
            + lut[d3 >> 8 & 0xff] + lut[d3 >> 16 & 0xff] + lut[d3 >> 24 & 0xff];
    };

    function empty(json) {
        for (p in json) {
            return false;
        }
        return true;
    };

    function Q(myDoc) {
        var ah = ["done", "onState", "listener", "onPagePrinted"];
        for (var i = 0; i < ah.length; i++) {
            var e = ah[i];
            if (myDoc[e]) {
                myDoc[e] = P(myDoc[e], false, i > 0);
                myDoc._hasCallback = true;
            }
        }
        if (myDoc.dragDesigner && myDoc.dragDesigner.ok) {
            myDoc.dragDesigner.ok = P(myDoc.dragDesigner.ok);
            myDoc._hasCallback = true;
        }
    };
    var ac;
    var private_url;
    var aj = [];
    aj[3] = "http://" + au + ":31227/api?type=service&";
    aj[4] = "http://" + au + ":31227/api?type=admin&";
    var ab = {};
    var ag = 0;
    var ai;
    var aD;

    function U() {
        setTimeout(function () {
            ajax(aj[1] + 'type=TICK&', {}, function (data, text, status) {
                if (status != 200 || data["api-error"]) {
                    on = false;
                    return;
                }
                U();
            });
        }, 30000);
    };

    function O() {
        aD = true;
        ajax(aj[1] + 'type=PULL&', {}, function (data, text, status) {
            if (status != 200 || data["api-error"]) {
                on = false;
                return;
            }
            try {
                if (data.event) {
                    ab[data.event].apply(null, data.params || [data.data]);
                    if (data.event.indexOf("fixed") != 0) {
                        delete ab[data.event];
                    }
                }
            } catch (e) {
            }
            if (empty(ab)) {
                aD = false;
            } else {
                O();
            }
        });
    };
    var on = false;
    var ap = false;
    var ao = [];

    function M(aa) {
        ao.push(aa);
        if (ao.length == 1) {
            var az = {
                method: "new",
                lic: typeof(LIC) != 'undefined' ? LIC.key : "",
                base: document.URL
            };
            var ar = "";
            if (forward) {
                az.password = password;
                ar = "forward=" + forward + "&";
            }
            ajax("http://" + au + ":31227/api?type=NEW&" + ar, az, function (data, r, status) {
                if (status != 200) {
                    ao.length = 0;
                    var error = ' 杰表云打印（JCP）未安装或未开启.';
                    if (!aB.silent) {
                        if (au !== '127.0.0.1') {
                            alert(error = "无法连接 JCP 站：" + au);
                            return log(error);
                        }
                        if (JCP.setup) {
                            if (JCP.setup.noSetupHandle) {
                                JCP.setup.noSetupHandle();
                            } else if (!ap) {
                                if (JCP.setup.noSetupMessage)
                                    error = JCP.setup.noSetupMessage;
                                alert(error);
                                if (JCP.setup.download) {
                                    ap = true;
                                    document.location.href = JCP.setup.download;
                                }
                            }
                        } else
                            alert(error);
                    }
                    return log(error);
                } else if (data["api-result"]) {
                    on = true;
                    ai = data["api-result"];
                    aj[0] = "http://" + au + ":31227/api?tab=" + ai + "&use=common" + "&" + ar;
                    aj[1] = "http://" + au + ":31227/api?tab=" + ai + "&" + ar;
                    aj[2] = "http://" + au + ":31227/api?type=UPDATE&";
                    aj[3] = "http://" + au + ":31227/api?type=service&";
                    U();
                    O();
                    while (ao.length) {
                        ao.shift()();
                    }
                } else {
                    ao.length = 0;
                    alert(data["api-error"]);
                    return log(data["api-error"]);
                }
            });
        }
    };

    function P(aa, json, fixed) {
        if (aa) {
            var index = fixed ? "fixed" : "event-" + ag++;
            ab[index] = !json ? aa : function (result) {
                result = eval("(" + result + ")");
                aa(result);
            };
            return ai + ":" + index;
        } else
            return "";
    };

    function H(aa) {
        if (!on) {
            M(aa);
        } else {
            aa();
        }
    };

    function I(data, text, status) {
        if (status != 200 || data["api-error"]) {
            if (status == 200 && data["api-error"] == 'NO_LICENSED_HOST') {
                var aq = data["aq"] || 0;
                if (aq == 0)
                    alert("未经绑定的主机：" + data["host"]);
                else if (aq == 1) {
                    alert("本版本为试用版，不支持在 " + data["host"] + " 上试用，请在 127.0.0.1 上试用.");
                } else if (aq == 2) {
                    alert("本版本为试用版，在 127.0.0.1上试用到期，购买请联系手机: (0)18969037811 .");
                }
                ab = {};
            }
            on = false;
            return;
        }
    };

    function R(target, param, data) {
        (!aD) && O();
        ajax(aj[target] + (param || ''), data, I);
    };
    var new_ = true;
    var aB = {};
    var self = null;
    return ((self = {
        "initialize": function () {
            return this;
        },
        "options": function (o) {
            if (o) {
                aB = o;
                return this;
            } else {
                return aB;
            }
        },
        "print": function (myDoc, prompt) {
            H(function () {
                myDoc = C(myDoc);
                Q(myDoc);
                R(1, null, {
                    method: "print",
                    params: [myDoc, prompt ? true : false]
                });
            })
        },
        "printPreview": function (myDoc, prompt) {
            H(function () {
                myDoc = C(myDoc);
                Q(myDoc);
                R(1, null, {
                    method: "printPreview",
                    params: [myDoc, prompt ? true : false]
                });
            })
        },
        "getVersion": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getVersion",
                    event: P(aa)
                });
            })
        },
        "getDefaultPrinter": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getDefaultPrinter",
                    event: P(aa)
                });
            })
        },
        "getPrinterCapability": function (printer, am, aa) {
            var al = function (result) {
                result = eval("(" + result + ")");
                aa(result.result);
            };
            H(function () {
                R(0, null, {
                    method: "getPrinterCapability",
                    params: [printer, am],
                    event: P(al)
                });
            })
        },
        "about": function () {
            H(function () {
                R(1, null, {
                    method: "about"
                });
            })
        },
        "exit": function () {
            H(function () {
                R(1, null, {
                    method: "exit"
                });
            })
        },
        "emptyCallback": function () {
        },
        "getPrinters": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getPrinters",
                    event: P(aa)
                });
            })
        },
        "getPrinterJobs": function (printer, aa) {
            H(function () {
                R(0, null, {
                    method: "getPrinterJobs",
                    params: [printer],
                    event: P(aa)
                });
            })
        },
        "getPapers": function (printer, aa) {
            H(function () {
                R(0, null, {
                    method: "getPapers",
                    params: [printer],
                    event: P(aa)
                });
            })
        },
        "isCustomPaperSupported": function (printer, aa) {
            H(function () {
                R(0, null, {
                    method: "isCustomPaperSupported",
                    params: [printer],
                    event: P(aa)
                });
            })
        },
        "addPaper": function (printer, name, width, height, aa) {
            H(function () {
                R(0, null, {
                    method: "addPaper",
                    params: [printer, name, width, height],
                    event: P(aa)
                });
            })
        },
        "isExcelInstalled": function (aa) {
            H(function () {
                R(0, null, {
                    method: "isExcelInstalled",
                    event: P(aa)
                });
            })
        },
        "isImplemented": function (method, aa) {
            H(function () {
                R(0, null, {
                    method: "isImplemented",
                    params: [method],
                    event: P(aa)
                });
            })
        },
        "getLocalMacAddress": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getLocalMacAddress",
                    event: P(aa)
                });
            })
        },
        "getCPUSerialNo": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getCPUSerialNo",
                    event: P(aa)
                });
            })
        },
        "setOffsetPage": function (offset, aa) {
            H(function () {
                R(0, null, {
                    method: "setOffsetPage",
                    params: [offset],
                    event: P(aa)
                });
            })
        },
        "isPrintableFileType": function (filetype, aa) {
            H(function () {
                R(0, null, {
                    method: "isPrintableFileType",
                    params: [filetype],
                    event: P(aa)
                });
            })
        },
        "setDragCSS": function (settingid, styles, aa) {
            H(function () {
                R(0, null, {
                    method: "setDragCSS",
                    params: [settingid, styles],
                    event: P(aa)
                });
            })
        },
        "clearSettings": function (settingid, aa) {
            H(function () {
                R(0, null, {
                    method: "clearSettings",
                    params: [settingid],
                    event: P(aa)
                });
            })
        },
        "getSettingsIds": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getSettingsIds",
                    event: P(aa, true)
                });
            })
        },
        "printTIFF": function (url, margins, how, aa) {
            H(function () {
                R(1, null, {
                    method: "printTIFF",
                    params: [url, margins, how],
                    event: P(aa)
                });
            })
        },
        "printDocument": function (file, sets, aa) {
            H(function () {
                Q(sets);
                if (sets["fileType"] == "pdf") {
                    sets.setup = JCP.setup.download;
                }
                R(1, null, {
                    method: "printDocument",
                    params: [file, sets],
                    event: P(aa)
                });
            })
        },
        "exportAsExcel": function (tableEl, path, showProgress, aa) {
            H(function () {
                var html = t(tableEl);
                R(1, null, {
                    method: "exportAsExcel",
                    params: [html, path, showProgress],
                    event: P(aa)
                });
            })
        },
        "setupNormalOffset": function (settingid, aa) {
            H(function () {
                R(1, null, {
                    method: "setupNormalOffset",
                    params: [settingid],
                    event: P(aa)
                });
            })
        },
        "download": function (url, file, aa) {
            H(function () {
                R(0, null, {
                    method: "download",
                    params: [url, file],
                    event: P(aa)
                });
            })
        },
        "printToImage": function (myDoc, path, aa) {
            H(function () {
                myDoc = C(myDoc);
                Q(myDoc);
                R(1, null, {
                    method: "printToImage",
                    params: [myDoc, path],
                    event: P(aa)
                });
            })
        },
        "exportAsImage": function (myDoc, type, aa) {
            if (au == X) {
                H(function () {
                    myDoc = C(myDoc);
                    R(1, null, {
                        method: "exportAsImage",
                        params: [myDoc, type],
                        event: P(function (file) {
                            var url = "http://" + au + ":31227/api?type=download&file=" + file;
                            aa(url);
                        })
                    });
                });
            } else
                getJCP(X).exportAsImage(myDoc, type, aa);
        },
        "exportAsPDF": function (myDoc, aa) {
            if (au == X) {
                H(function () {
                    myDoc = C(myDoc);
                    R(1, null, {
                        method: "exportAsPDF",
                        params: [myDoc],
                        event: P(function (file) {
                            var url = "http://" + au + ":31227/api?type=download&file=" + file;
                            aa(url);
                        })
                    });
                });
            } else
                getJCP(X).exportAsPDF(myDoc, aa);
        },
        "exportAsTIFF": function (myDoc, aa) {
            if (au == X) {
                H(function () {
                    myDoc = C(myDoc);
                    R(1, null, {
                        method: "exportAsTIFF",
                        params: [myDoc],
                        event: P(function (file) {
                            var url = "http://" + au + ":31227/api?type=download&file=" + file;
                            aa(url);
                        })
                    });
                });
            } else
                getJCP(X).exportAsTIFF(myDoc, aa);
        },
        "configUpdate": function (an, aN, aa) {
            H(function () {
                R(2, null, {
                    method: "configUpdate",
                    params: [an, aN],
                    event: P(aa)
                });
            })
        },
        "getFonts": function (aa) {
            H(function () {
                R(0, null, {
                    method: "getFonts",
                    event: P(aa)
                });
            })
        },
        "copy": function (data, format, aa) {
            H(function () {
                R(0, null, {
                    method: "copy",
                    params: [data, format],
                    event: P(aa)
                });
            })
        },
        "copied": function (format, aa) {
            H(function () {
                R(0, null, {
                    method: "copied",
                    params: [format],
                    event: P(aa)
                });
            })
        },
        "writeString": function (file, af, data, aa) {
            H(function () {
                R(0, null, {
                    method: "writeString",
                    params: [file, af, data],
                    event: P(aa)
                });
            })
        },
        "writeBase64": function (file, data, aa) {
            H(function () {
                R(0, null, {
                    method: "writeBase64",
                    params: [file, data],
                    event: P(aa)
                });
            })
        },
        "readString": function (file, af, aa) {
            H(function () {
                R(0, null, {
                    method: "readString",
                    params: [file, af],
                    event: P(aa)
                });
            })
        },
        "readBase64": function (file, aa) {
            H(function () {
                R(0, null, {
                    method: "readBase64",
                    params: [file],
                    event: P(aa)
                });
            })
        },
        "readHTML": function (file, defencode, aa) {
            H(function () {
                R(0, null, {
                    method: "readHTML",
                    params: [file, defencode],
                    event: P(aa)
                });
            })
        },
        "chooseFile": function (ext, defext, saveselect, aa) {
            H(function () {
                R(1, null, {
                    method: "chooseFile",
                    params: [ext, defext, saveselect],
                    event: P(aa)
                });
            })
        },
        "showPageSetupDialog": function (aa) {
            var al = function (result) {
                aa(result ? eval("(" + result + ")") : null);
            };
            H(function () {
                R(0, null, {
                    method: "showPageSetupDialog",
                    event: P(al)
                });
            })
        },
        "getLastSettings": function (settingid, aa) {
            H(function () {
                R(0, null, {
                    method: "getLastSettings",
                    params: [settingid],
                    event: P(aa)
                });
            })
        },
        "getAbsoluteURL": function (relative, base, aa) {
            var stack = base.split("/"), parts = relative.split("/");
            stack.pop();
            for (var i = 0; i < parts.length; i++) {
                if (parts[i] == ".")
                    continue;
                if (parts[i] == "..")
                    stack.pop();
                else
                    stack.push(parts[i]);
            }
            (aa || this.nothing).call(this, stack.join("/"));
        },
        "setLastSettings": function (settingid, ad, aa) {
            H(function () {
                R(0, null, {
                    method: "setLastSettings",
                    params: [settingid, ad],
                    event: P(aa)
                });
            })
        },
        "setDefaultPrinter": function (printer, aa) {
            H(function () {
                R(0, null, {
                    method: "setDefaultPrinter",
                    params: [printer],
                    event: P(aa)
                });
            })
        },
        "openFile": function (file, aa) {
            H(function () {
                R(1, null, {
                    method: "openFile",
                    params: [file],
                    event: P(aa)
                });
            })
        },
        "getPrinterInfo": function (printer, aa) {
            H(function () {
                R(0, null, {
                    method: "getPrinterInfo",
                    params: [printer],
                    event: P(aa)
                });
            })
        },
        "showHTMLDialog": function (url, width, height, aG) {
            H(function () {
                var options = [];
                if (width)
                    options.push("dialogWidth:" + width);
                if (height)
                    options.push("dialogHeight:" + height);
                if (!aG)
                    options.push("aG:yes");
                R(1, null, {
                    method: "showHTMLDialog",
                    params: [0, url, options.join(";")]
                });
            })
        },
        "getPrinterStatus": function (printer, Z, aa) {
            H(function () {
                R(0, null, {
                    method: "getPrinterStatus",
                    params: [printer, Z],
                    event: P(aa)
                });
            })
        },
        "nothing": function () {
        },
        "setPrintBackground": function (back, aa) {
            H(function () {
                R(0, null, {
                    method: "setPrintBackground",
                    params: [back],
                    event: P(aa)
                });
            })
        },
        "getServerId": function (url, aa) {
            H(function () {
                ajax(aj[3], {
                    method: "getServerId",
                    url: url
                }, aa, true);
            });
        },
        "findJCPs": function (aa, seconds) {
            H(function () {
                ajax(aj[3], {
                    method: "findJCPs",
                    seconds: seconds || 5
                }, aa, true);
            });
        },
        "connect": function (aI, ax, aa) {
            H(function () {
                ajax(aj[4], {
                    method: "connect",
                    aI: aI,
                    aw: ax
                }, aa, true);
            });
        }
    })).initialize();
};
var ak = {};

function getJCP(au, forward, password) {
    au = au || "127.0.0.1";
    var id = au + (forward || "");
    if (!ak[id]) {
        ak[id] = new Jcp(au, forward || "", password || "");
    }
    return ak[id];
};

function L() {
    getJP().exit()
};
var JSON = JSON || {};
var JSONstringify = JSON.stringify || (function () {
    "use strict";
    var aH = /[\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;

    function f(n) {
        return n < 10 ? "0" + n : n;
    };

    function T() {
        return this.valueOf();
    };
    if (typeof Date.prototype.aL !== "function") {
        Date.prototype.aL = function () {
            return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate()) + "T" + f(this.getUTCHours()) + ":"
                + f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds()) + "Z" : null;
        };
        Boolean.prototype.aL = T;
        Number.prototype.aL = T;
        String.prototype.aL = T;
    }
    var as;
    var indent;
    var meta;
    var aE;

    function J(string) {
        aH.lastIndex = 0;
        return aH.test(string) ? "\"" + string.replace(aH, function (a) {
            var c = meta[a];
            return typeof c === "string" ? c : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4);
        }) + "\"" : "\"" + string + "\"";
    };

    function str(key, at) {
        var i;
        var k;
        var v;
        var length;
        var ay = as;
        var aC;
        var value = at[key];
        if (value && typeof value === "object" && typeof value.aL === "function") {
            value = value.aL(key);
        }
        if (typeof aE === "function") {
            value = aE.call(at, key, value);
        }
        switch (typeof value) {
            case "string" :
                return J(value);
            case "number" :
                return isFinite(value) ? String(value) : "null";
            case "boolean" :
            case "null" :
                return String(value);
            case "object" :
                if (!value) {
                    return "null";
                }
                as += indent;
                aC = [];
                if (Object.prototype.toString.apply(value) === "[object Array]") {
                    length = value.length;
                    for (i = 0; i < length; i += 1) {
                        aC[i] = str(i, value) || "null";
                    }
                    v = aC.length === 0 ? "[]" : as ? "[\n" + as + aC.join(",\n" + as) + "\n" + ay + "]" : "[" + aC.join(",") + "]";
                    as = ay;
                    return v;
                }
                if (aE && typeof aE === "object") {
                    length = aE.length;
                    for (i = 0; i < length; i += 1) {
                        if (typeof aE[i] === "string") {
                            k = aE[i];
                            v = str(k, value);
                            if (v) {
                                aC.push(J(k) + (as ? ": " : ":") + v);
                            }
                        }
                    }
                } else {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = str(k, value);
                            if (v) {
                                aC.push(J(k) + (as ? ": " : ":") + v);
                            }
                        }
                    }
                }
                v = aC.length === 0 ? "{}" : as ? "{\n" + as + aC.join(",\n" + as) + "\n" + ay + "}" : "{" + aC.join(",") + "}";
                as = ay;
                return v;
        }
    };
    if (typeof JSON.stringify !== "function") {
        meta = {
            "\b": "\\b",
            "\t": "\\t",
            "\n": "\\n",
            "\f": "\\f",
            "\r": "\\r",
            "\"": "\\\"",
            "\\": "\\\\"
        };
        JSON.stringify = function (value, aF, space) {
            var i;
            as = "";
            indent = "";
            if (typeof space === "number") {
                for (i = 0; i < space; i += 1) {
                    indent += " ";
                }
            } else if (typeof space === "string") {
                indent = space;
            }
            aE = aF;
            if (aF && typeof aF !== "function" && (typeof aF !== "object" || typeof aF.length !== "number")) {
                throw new Error("JSON.stringify");
            }
            return str("", {
                "": value
            });
        };
    }
    return JSON.stringify;
}());
var Y = null;

function showLoading(by) {
    if (!Y) {
        Y = document.createElement("img");
        Y.src = "http://print.jatools.com/jcp/0.99/image/loading1.gif";
        Y.style.verticalAlign = "middle";
    }
    Y.style.display = "inline";
    var aJ = document.getElementById(by);
    aJ.parentNode.insertBefore(Y, aJ.nextSibling);
};

function hideLoading() {
    if (Y) {
        Y.style.display = "none";
    }
}