
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.concord.framework.logging;

public interface LoggingConstants{
    public static final String LOGGING_GLOBAL_FILTER_PROPERTY = "org.concord.logging.globalfilter";
    public static final String LOGGING_ROOT_PATH_PROPERTY = "org.concord.logging.rootpath";
    public static final String LOGGING_ACTIVITY_ID_PROPERTY = "org.concord.pedagogica.activity_id";
    public static final String LOGGING_ACTIVITY_INSTANCE_ID_PROPERTY = "org.concord.pedagogica.activity_instance_id";
    public static final String LOGGING_CLASS_ID_PROPERTY = "org.concord.pedagogica.class_id";
    public static final String LOGGING_TOPIC_ID_PROPERTY = "org.concord.pedagogica.topic_id";
    public static final String LOGGING_SCHOOL_YEAR_PROPERTY = "org.concord.logging.school_year";
    public static final String LOGGING_USER_ID_PROPERTY = "org.concord.pedagogica.user_id";
    public static final String LOGGING_PASSWORD_HASH_PROPERTY = "org.concord.pedagogica.password_hash";
    public static final String LOGGING_BLOWFISH_PROPERTY = "org.concord.pedagogica.encrypted_blowfish_key";
    public static final String LOGGING_PRIVATE_PROPERTY = "org.concord.pedagogica.encrypted_private_key";
}
