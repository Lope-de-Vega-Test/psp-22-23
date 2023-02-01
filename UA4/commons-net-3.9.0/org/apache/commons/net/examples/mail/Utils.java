/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.net.examples.mail;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Utilities for mail examples
 */
class Utils {

    /**
     * If the initial password is: '*' - replace it with a line read from the system console '-' - replace it with next line from STDIN 'ABCD' - if the input is
     * all upper case, use the field as an environment variable name
     *
     * Note: there are no guarantees that the password cannot be snooped.
     *
     * Even using the console may be subject to memory snooping, however it should be safer than the other methods.
     *
     * STDIN may require creating a temporary file which could be read by others Environment variables may be visible by using PS
     */
    static String getPassword(final String username, String password) throws IOException {
        if ("-".equals(password)) { // stdin
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            password = in.readLine();
        } else if ("*".equals(password)) { // console
            final Console con = System.console(); // Java 1.6
            if (con == null) {
                throw new IOException("Cannot access Console");
            }
            final char[] pwd = con.readPassword("Password for " + username + ": ");
            password = new String(pwd);
        } else if (password.equals(password.toUpperCase(Locale.ROOT))) { // environment variable name
            final String tmp = System.getenv(password);
            if (tmp != null) { // don't overwrite if variable does not exist (just in case password is all uppers)
                password = tmp;
            }
        }
        return password;
    }

    private Utils() {
        // not instantiable
    }

}
